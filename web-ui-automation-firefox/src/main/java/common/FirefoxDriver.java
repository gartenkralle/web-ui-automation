package common;

import org.openqa.selenium.WebDriver;

final class FirefoxDriver extends Driver
{
    private FirefoxDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        return Driver.getDriver();
    }
}
