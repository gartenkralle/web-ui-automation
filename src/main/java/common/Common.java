package common;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

final class Common
{
    private static WebDriver driver;
    private static FluentWait<WebDriver> wait;
    
    private final static int STANDARD_TIMEOUT_IN_SECONDS = 10;
    private final static int INTERVALL_IN_MILLISECONDS = 200;
    
    private final static By ANY_ELEMENT = By.xpath("//*");
    
    private Common()
    {
        
    }
    
    public static class Action
    {
        private Action()
        {
            
        }
        
        public static void visitUrl(String url)
        {
            driver.get(url);
        }
        
        public static void pressKey(Keys key)
        {
            sendKeys(ANY_ELEMENT, key);
        }
        
        public static <T> void fillField(By location, T value)
        {
            String s = String.valueOf(value);
            sendKeys(location, s);
        }
        
        public static void clickElement(By location)
        {
            getClickableWebElement(location).click();
        }
    }
    
    public static class Verify
    {
        private Verify()
        {
            
        }
        
        public static void appeared(By location)
        {
            getVisibleWebElement(location);
        }
    }
    
    public static class DataReceive
    {
        private DataReceive()
        {
            
        }
    }
    
    public static class Setting
    {
        private Setting()
        {
            
        }
        
        public static void setup(WebDriver driver)
        {
            Common.driver = driver;
            Common.wait = new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS).pollingEvery(INTERVALL_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        }
    }
    
    private static void sendKeys(By location, CharSequence charSequence)
    {
        getVisibleWebElement(location).sendKeys(charSequence);
    }
        
    private static WebElement getVisibleWebElement(By location)
    {
        return handleTimeout(wait::until, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    private static WebElement getClickableWebElement(By location)
    {
        return handleTimeout(wait::until, ExpectedConditions.elementToBeClickable(location));
    }
    
    private static <T1, R> R handleTimeout(Function<T1, R> functionPointer, T1 by)
    {
        R result = null;
        
        try
        {
            result = functionPointer.apply(by);
        }
        catch(TimeoutException e)
        {
            Assert.fail();;
        }
        
        return result;
    }

}