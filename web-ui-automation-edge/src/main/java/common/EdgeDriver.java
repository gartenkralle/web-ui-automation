package common;

import org.openqa.selenium.WebDriver;

final class EdgeDriver extends Driver
{
    private EdgeDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        return Driver.getDriver();
    }
}
