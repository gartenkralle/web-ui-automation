package common;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;

final class FirefoxDriver
{
    private FirefoxDriver()
    {
        
    }
    
    public static WebDriver getFirefoxDriver()
    {
        WebDriver webDriver =  null;
        
        if(SystemUtils.IS_OS_LINUX)
        {
            webDriver = getLinuxFirefoxDriver();
        }
        else if(SystemUtils.IS_OS_WINDOWS)
        {
            webDriver = getWindowsFirefoxDriver();
        }
        else if(SystemUtils.IS_OS_MAC)
        {
            webDriver = getMacFirefoxDriver();
        }
        
        return webDriver;
    }
    
    private static WebDriver getLinuxFirefoxDriver()
    {
        throw new UnsupportedOperationException();
    }
    
    private static WebDriver getWindowsFirefoxDriver()
    {
        throw new UnsupportedOperationException();
    }
    
    private static WebDriver getMacFirefoxDriver()
    {
        throw new UnsupportedOperationException();
    }
}
