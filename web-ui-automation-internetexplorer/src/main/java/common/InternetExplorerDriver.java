package common;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;

final class InternetExplorerDriver extends Driver
{
    private InternetExplorerDriver()
    {
        
    }
    
    public static WebDriver getDriver()
    {
        WebDriver webDriver = null;
        
        if(SystemUtils.IS_OS_WINDOWS)
        {
            webDriver = Driver.getDriver();
        }
        
        return webDriver;
    }
}
