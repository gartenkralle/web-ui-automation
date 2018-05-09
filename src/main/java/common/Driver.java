package common;

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
        Driver.instance = new ChromeDriver();
        
        Common.Setting.setup(Driver.instance);
    }
    
    public static void quit()
    {
        Driver.instance.quit();
    }
}
