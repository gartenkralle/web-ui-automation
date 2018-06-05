package common;

import org.openqa.selenium.WebDriver;

import common.Driver;

final class ChromeDriver extends Driver
{
    private ChromeDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        return Driver.getDriver();
    }
}
