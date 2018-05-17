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

import common.Driver;

final class ChromeDriver
{
    private ChromeDriver()
    {
        
    }
    
    public static WebDriver getChromeDriver()
    {
        WebDriver webDriver =  null;
        
        if(SystemUtils.IS_OS_LINUX)
        {
            webDriver = getLinuxChromeDriver();
        }
        else if(SystemUtils.IS_OS_WINDOWS)
        {
            webDriver = getWindowsChromeDriver();
        }
        else if(SystemUtils.IS_OS_MAC)
        {
            webDriver = getMacChromeDriver();
        }
        
        return webDriver;
    }
    
    private static WebDriver getLinuxChromeDriver()
    {
        return getChromeDriver("linux/chromedriver");
    }
    
    private static WebDriver getWindowsChromeDriver()
    {
        return getChromeDriver("windows/chromedriver.exe");
    }
    
    private static WebDriver getMacChromeDriver()
    {
        return getChromeDriver("mac/chromedriver");
    }
    
    private static WebDriver getChromeDriver(String chromedriver)
    {
        final String resource = Driver.class.getClassLoader().getResource(chromedriver).getFile();
        
        if(new File(resource).isFile())
        {
            chromedriver = resource;
        }
        else if(!new File(chromedriver).isFile())
        {
            copyResourceFileToChildProject(chromedriver);
        }
        
        if(SystemUtils.IS_OS_LINUX)
        {
            setExecutePermission(chromedriver);
        }
        
        System.setProperty("webdriver.chrome.driver", chromedriver);
        
        return new org.openqa.selenium.chrome.ChromeDriver();
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
}
