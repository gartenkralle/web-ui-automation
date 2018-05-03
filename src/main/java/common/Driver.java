package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Driver
{
    private final static int STANDARD_TIMEOUT_IN_SECONDS = 10;
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    
    private Driver()
    {
        
    }
    
    public static void setup()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS);
        
        UserInterface.Setting.setup(driver, wait);
    }
    
    public static void quit()
    {
        driver.quit();
    }
}
