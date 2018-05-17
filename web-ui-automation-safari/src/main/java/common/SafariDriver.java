package common;

import org.openqa.selenium.WebDriver;

final class SafariDriver extends Driver
{
    private SafariDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        return new org.openqa.selenium.safari.SafariDriver();
    }
}
