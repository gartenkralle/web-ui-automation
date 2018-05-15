package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
        String chromedriver = "chromedriver.exe";
        final String resource = Driver.class.getClassLoader().getResource(chromedriver).getFile();
        
        if(new File(resource).isFile())
        {
            chromedriver = resource;
        }
        else if(!new File(chromedriver).isFile())
        {
            try
            {
                Path temp = Files.createTempFile("temp", ".tmp");
                Files.copy(Driver.class.getClassLoader().getResourceAsStream(chromedriver), temp, StandardCopyOption.REPLACE_EXISTING);
                FileInputStream inputStream = new FileInputStream(temp.toFile());
                
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                
                File file = new File(chromedriver);
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
        
        System.setProperty("webdriver.chrome.driver", chromedriver);
        
        return new ChromeDriver();
    }
}
