package common;

import org.openqa.selenium.WebDriver;

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
}
