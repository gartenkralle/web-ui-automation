package common;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unitils.reflectionassert.ReflectionAssert;

final class Common
{
    private static WebDriver driver;
    private static FluentWait<WebDriver> wait;
    
    private final static int STANDARD_TIMEOUT_IN_SECONDS = 10;
    private final static int INTERVALL_IN_MILLISECONDS = 200;
    
    private final static By ANY_ELEMENT = By.xpath(StringCollection.XPath.ANY_ELEMENT);
    
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
            handleTimeout((Consumer<String>)driver::get, url);
        }
        
        public static void pressKey(Keys key)
        {
            sendKeys(ANY_ELEMENT, key);
        }
        
        public static <T> void fillField(By location, T value)
        {
            sendKeys(location, String.valueOf(value));
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

        public static <T> void equals(T expectedValue, T actualValue)
        {
            ReflectionAssert.assertReflectionEquals(expectedValue, actualValue);
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
            
            Timeouts timeouts = driver.manage().timeouts();
            
            timeouts.implicitlyWait(STANDARD_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            timeouts.pageLoadTimeout(STANDARD_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            
            Common.wait = new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS).pollingEvery(Duration.ofMillis(INTERVALL_IN_MILLISECONDS));
        }
    }
    
    private static void sendKeys(By location, CharSequence charSequence)
    {
        getVisibleWebElement(location).sendKeys(charSequence);
    }
        
    private static WebElement getVisibleWebElement(By location)
    {
        return handleTimeout((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    private static WebElement getClickableWebElement(By location)
    {
        return handleTimeout((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExpectedConditions.elementToBeClickable(location));
    }
    
    private static <T1, R> R handleTimeout(Function<T1, R> functionPointer, T1 arg1)
    {
        R result = null;
        
        try
        {
            result = functionPointer.apply(arg1);
        }
        catch(TimeoutException e)
        {
            errorMessage(arg1);
        }
        
        return result;
    }
    
    private static <T> void handleTimeout(Consumer<T> functionPointer, T arg1)
    {
        try
        {
            functionPointer.accept(arg1);
        }
        catch(TimeoutException e)
        {
            errorMessage(arg1);
        }
    }
    
    private static <T> void errorMessage(T arg1)
    {
        Assert.fail(System.lineSeparator() + StringCollection.Error.TIMEOUT_HEADER + arg1.toString());;
    }
}