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

final class Driver
{
    private static WebDriver instance;
    
    private Driver()
    {
        
    }
    
    public static void setup()
    {
        Driver.instance = getChromeDriver();
        
        Common.Setting.setup(Driver.instance);
    }
    
    public static void quit()
    {
        Driver.instance.quit();
    }
    
    private static ChromeDriver getChromeDriver()
    {
        ChromeDriver chromeDriver =  null;
        
        if(SystemUtils.IS_OS_LINUX)
        {
            chromeDriver = getLinuxChromeDriver();
        }
        else if(SystemUtils.IS_OS_WINDOWS)
        {
            chromeDriver = getWindowsChromeDriver();
        }
        
        return chromeDriver;
    }
    
    private static ChromeDriver getLinuxChromeDriver()
    {
        return getChromeDriver("chromedriver");
    }
    
    private static ChromeDriver getWindowsChromeDriver()
    {
        return getChromeDriver("chromedriver.exe");
    }
    
    private static ChromeDriver getChromeDriver(String chromedriver)
    {
        final String resource = Driver.class.getClassLoader().getResource(chromedriver).getFile();
        
        if(new File(resource).isFile())
        {
            chromedriver = resource;
        }
        else if(!new File(chromedriver).isFile())
        {
            CopyResourceFileToChildProject(chromedriver);
        }
        
        if(SystemUtils.IS_OS_LINUX)
        {
            setExecutePermission(chromedriver);
        }
        
        System.setProperty("webdriver.chrome.driver", chromedriver);
        
        return new ChromeDriver();
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
    
    private static void CopyResourceFileToChildProject(String filename)
    {
        try
        {
            Path temp = Files.createTempFile("temp", ".tmp");
            Files.copy(Driver.class.getClassLoader().getResourceAsStream(filename), temp, StandardCopyOption.REPLACE_EXISTING);
            FileInputStream inputStream = new FileInputStream(temp.toFile());
            
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            
            File file = new File(filename);
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
}
