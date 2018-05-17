package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class Driver
{
    private static WebDriver instance;
    
    public Driver()
    {
        
    }
    
    public static void setup(WebDriver webdriver)
    {
        Driver.instance = webdriver;
        
        Common.Setting.setup(Driver.instance);
    }
    
    public static void quit()
    {
        Driver.instance.quit();
    }
    
    protected static WebDriver getLinuxDriver(String driverName, String property)
    {
        return getDriver("linux", driverName, property);
    }
    
    protected static WebDriver getWindowsDriver(String driverName, String property)
    {
        return getDriver("windows", driverName, property);
    }
    
    protected static WebDriver getMacDriver(String driverName, String property)
    {
        return getDriver("mac", driverName, property);
    }
    
    private static WebDriver getDriver(String driverPath, String driverName, String property)
    {
        String driverLocation = driverPath + "/" + driverName;
        
        final String resource = Driver.class.getClassLoader().getResource(driverLocation).getFile();
        
        if(new File(resource).isFile())
        {
            driverLocation = resource;
        }
        else if(!new File(driverLocation).isFile())
        {
            copyResourceFileToChildProject(driverLocation);
        }
        
        if(SystemUtils.IS_OS_LINUX)
        {
            setExecutePermission(driverLocation);
        }
        
        System.setProperty(property, driverLocation);
        
        return getDriver(property);
    }
    
    private static WebDriver getDriver(String property)
    {
        WebDriver webDriver = null;
        
        if(property.contains("chrome"))
        {
            return new ChromeDriver();
        }
        else if(property.contains("gecko"))
        {
            return new FirefoxDriver();
        }
        
        return webDriver;
    }
    
    private static void copyResourceFileToChildProject(String filename)
    {
        try
        {
            Path temp = Files.createTempFile("temp", ".tmp");
            Files.copy(Driver.class.getClassLoader().getResourceAsStream(filename), temp, StandardCopyOption.REPLACE_EXISTING);
            FileInputStream inputStream = new FileInputStream(temp.toFile());
            
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            
            File file = new File(filename);
            file.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private static void setExecutePermission(String driver)
    {
        setExecutePermission(new File(driver));
    }
    
    private static void setExecutePermission(File file)
    {
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        
        try
        {
            Files.setPosixFilePermissions(file.toPath(), perms);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
