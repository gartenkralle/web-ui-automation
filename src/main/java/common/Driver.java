package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

final class Driver
{
    private static WebDriver driver;
    
    private Driver()
    {
        
    }
    
    public static void setup()
    {
        Common.Setting.setup(new ChromeDriver());
    }
    
    public static void quit()
    {
        driver.quit();
    }
}
