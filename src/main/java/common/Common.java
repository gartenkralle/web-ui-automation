package common;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
            Common.visitUrl(url);
        }
        
        public static void pressKey(Keys key)
        {
            Common.getVisibleWebElement(ANY_ELEMENT).sendKeys(key);
        }
        
        public static <T> void fillField(By location, T value)
        {
            Common.getVisibleWebElement(location).sendKeys(String.valueOf(value));
        }
        
        public static void clickElement(By location)
        {
            Common.getClickableWebElement(location).click();
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
        
        public static void _true(boolean condition)
        {
            equals(true, condition);
        }
        
        public static void _false(boolean condition)
        {
            equals(false, condition);
        }
        
        public static void url(String url)
        {
            equals(url, driver.getCurrentUrl());
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
    
    private static void visitUrl(String url)
    {
        handleException((Consumer<String>)driver::get, Common::getTimeoutMessage, url);
    }

    private static WebElement getVisibleWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    private static WebElement getClickableWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    private static <T1, R> R handleException(Function<T1, R> executeFunction, Function<T1, String> exceptionMessageFunction, T1 arg1)
    {
        R result = null;
        
        try
        {
            result = executeFunction.apply(arg1);
        }
        catch(RuntimeException e)
        {
            errorMessage(exceptionMessageFunction.apply(arg1));
        }
        
        return result;
    }
    
    private static <T> void handleException(Consumer<T> executeFunction, Function<T, String> exceptionMessageFunction, T arg1)
    {
        try
        {
            executeFunction.accept(arg1);
        }
        catch(RuntimeException e)
        {
            errorMessage(exceptionMessageFunction.apply(arg1));
        }
    }
    
    private static <T> String getTimeoutMessage(T arg1)
    {
        return getMessage(StringCollection.Error.TIMEOUT_HEADER, arg1.toString());
    }
    
    private static String getMessage(String header, String content)
    {
        return System.lineSeparator() + header + content;
    }
    
    private static void errorMessage(String errorMessage)
    {
        Assert.fail(errorMessage);
    }
}