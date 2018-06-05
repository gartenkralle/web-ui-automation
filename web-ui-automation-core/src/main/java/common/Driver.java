package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class Driver
{
    private static WebDriver instance;
    private static FileSystem jarFileSystem;
    
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
    
    protected static WebDriver getDriver()
    {
        String driverPathName = getDriverPathName();
        
        if(!isResourceInChildProject(driverPathName))
        {
            copyResourceFileToChildProject(driverPathName);
            
            if(SystemUtils.IS_OS_LINUX)
            {
                setExecutePermission(driverPathName);
            }
        }
        
        String property = getProperty(driverPathName);
        System.setProperty(property, driverPathName);
        
        return getDriver(property);
    }
    
    private static String getDriverPathName()
    {
        String folder = "driver";
        
        folder = folder + "/" + getNewestContent(folder);
        folder = folder + "/" + getOperatingSystem();
        folder = folder + "/" + getNewestContent(folder);
        
        return folder;
    }
    
    private static String getNewestContent(String folder)
    {
        List<String> absoluteContents = getContent(folder);
        
        String relativeContent = absoluteContents.get(absoluteContents.size() - 1);
        relativeContent = relativeContent.replaceAll("\\\\", "/");
        
        String[] relativeContentSplit = relativeContent.split("/");
        
        return relativeContentSplit[relativeContentSplit.length - 1];
    }
    
    private static List<String> getContent(String folderName)
    {
        URI uri = null;
        
        try
        {
            uri = Driver.class.getClassLoader().getResource(folderName).toURI();
        }
        catch (URISyntaxException e1)
        {
            e1.printStackTrace();
        }
        
        Path myPath = null;
        
        if (uri.getScheme().equals("jar"))
        {
            try
            {
                if(jarFileSystem == null)
                {
                    jarFileSystem = FileSystems.newFileSystem(Driver.class.getClassLoader().getResource("driver").toURI(), Collections.<String, Object>emptyMap());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            myPath = jarFileSystem.getPath(folderName);
        }
        else
        {
            myPath = Paths.get(uri);
        }
        
        Stream<Path> walk = null;
        
        try
        {
            walk = Files.walk(myPath, 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        List<String> result = new ArrayList<String>();
        
        for (Iterator<Path> it = walk.iterator(); it.hasNext();)
        {
            result.add(it.next().toString());
        }
        
        return result;
    }
    
    private static boolean isResourceInChildProject(String pathname)
    {
        return new File(pathname).isFile();
    }
    
    private static WebDriver getDriver(String property)
    {
        WebDriver webDriver = null;
        
        if(property.contains("chrome"))
        {
            webDriver = new ChromeDriver(getChromeOptions());
        }
        else if(property.contains("gecko"))
        {
            webDriver = new FirefoxDriver();
        }
        else if(property.contains("ie"))
        {
            webDriver = new InternetExplorerDriver();
        }
        else if(property.contains("edge"))
        {
            webDriver = new EdgeDriver();
        }
        
        return webDriver;
    }
    
    private static String getOperatingSystem()
    {
        String operatingSystem = null;
        
        if(SystemUtils.IS_OS_LINUX)
        {
            operatingSystem = "linux";
        }
        else if(SystemUtils.IS_OS_WINDOWS)
        {
            operatingSystem = "windows";
        }
        else if(SystemUtils.IS_OS_MAC)
        {
            operatingSystem = "mac";
        }
        
        return operatingSystem;
    }
    
    private static String getProperty(String driverPathName)
    {
        String property = null;
        
        if(driverPathName.contains("chrome"))
        {
            property = "webdriver.chrome.driver";
        }
        else if(driverPathName.contains("edge"))
        {
            property = "webdriver.edge.driver";
        }
        else if(driverPathName.contains("gecko"))
        {
            property = "webdriver.gecko.driver";
        }
        else if(driverPathName.contains("ie"))
        {
            property = "webdriver.ie.driver";
        }
        
        return property;
    }
    
    private static ChromeOptions getChromeOptions()
    {
        ChromeOptions options = new ChromeOptions();
        
        String property = System.getenv("TRAVIS");
        
        if((property != null) && (property.equals("true")))
        {
            options.addArguments("headless");
        }
        
        return options;
    }
    
    private static void copyResourceFileToChildProject(String pathname)
    { 
        try
        {
            Path temp = Files.createTempFile("temp", ".tmp");
            Files.copy(Driver.class.getClassLoader().getResourceAsStream(pathname), temp, StandardCopyOption.REPLACE_EXISTING);
            FileInputStream inputStream = new FileInputStream(temp.toFile());
            
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            
            File file = new File(pathname);
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
    
    private static void setExecutePermission(String pathname)
    {
        setExecutePermission(new File(pathname));
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
