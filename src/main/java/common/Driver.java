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
        driver = new ChromeDriver();
        
        UserInterface.Setting.setup(driver);
    }
    
    public static void quit()
    {
        driver.quit();
    }
}
