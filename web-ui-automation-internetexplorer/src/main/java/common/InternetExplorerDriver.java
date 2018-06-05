package common;

import org.openqa.selenium.WebDriver;

final class InternetExplorerDriver extends Driver
{
    private InternetExplorerDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        return Driver.getDriver();
    }
}
