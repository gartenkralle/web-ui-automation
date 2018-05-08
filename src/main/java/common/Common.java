package common;

import java.time.Duration;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
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
        
        public static <T> void fillField(By location, T value)
        {
            Common.getVisibleWebElement(location).sendKeys(String.valueOf(value));
        }
        
        public static void clickElement(By location)
        {
            Common.getClickableWebElement(location).click();
        }
        
        public static void chooseDropDownItem(By location, String item)
        {
            Common.chooseDropDownItem(location, item);
        }
        
        public static void selectDefaultFrame()
        {
            Common.selectDefaultFrame();
        }
        
        public static void selectFrame(String iframeNameOrId)
        {
            Common.selectFrame(iframeNameOrId);
        }
        
        public static void clickRadioButton(By location)
        {
            Common.getClickableWebElement(location).click();
        }
        
        public static void pressEnter()
        {
            Common.pressKey(Keys.ENTER);
        }
        
        public static void pressEscape()
        {
            Common.pressKey(Keys.ESCAPE);
        }

        public static void checkCheckbox(By location)
        {
            if(!DataReceive.isChecked(location))
            {
                getClickableWebElement(location).click();
            }
        }

        public static void uncheckCheckbox(By location)
        {
            if(DataReceive.isChecked(location))
            {
                getClickableWebElement(location).click();
            }
        }
    }
    
    public static class Verify
    {
        private Verify()
        {
            
        }
        
        public static void appeared(By location)
        {
            Common.getVisibleWebElement(location);
        }
        
        public static void available(By location)
        {
            Common.getPresentWebElement(location);
        }
        
        public static void disappeared(By location)
        {
            Common.disappeared(location);
        }
        
        public static <T> void equals(T expectedValue, T actualValue)
        {
            Common.equals(expectedValue, actualValue);
        }
        
        public static <T> void notEquals(T unexpectedValue, T actualValue)
        {
            Common.notEquals(unexpectedValue, actualValue);
        }
        
        public static void _true(boolean condition)
        {
            Common._true(condition);
        }
        
        public static void _false(boolean condition)
        {
            Common._false(condition);
        }
        
        public static void url(String url)
        {
            Common.equals(url, driver.getCurrentUrl());
        }
        
        public static void contains(String containmentValue, String actualValue)
        {
            Common.contains(containmentValue, actualValue);
        }
        
        public static void checked(By location)
        {
            Common.checked(location);
        }
        
        public static void unchecked(By location)
        {
            Common.unchecked(location);
        }
        
        public static void enabled(By location)
        {
            Common.enabled(location);
        }
        
        public static void disabled(By location)
        {
            Common.disabled(location);
        }

        public static void contains(String expectedValue, List<String> actualValues)
        {
            Common.contains(expectedValue, actualValues);
        }

        public static void contains(List<String> expectedValues, List<String> actualValues)
        {
            Common.contains(expectedValues, actualValues);
        }
    }
    
    public static class DataReceive
    {
        private DataReceive()
        {
            
        }
        
        public static boolean isChecked(By location)
        {
            return Common.getVisibleWebElement(location).isSelected();
        }

        public static boolean isUnchecked(By location)
        {
            return !Common.getVisibleWebElement(location).isSelected();
        }

        public static boolean isEnabled(By location)
        {
            return Common.getVisibleWebElement(location).isEnabled();
        }

        public static boolean isDisabled(By location)
        {
            return !Common.getVisibleWebElement(location).isEnabled();
        }

        public static boolean isAvailable(By location)
        {
            return Common.isAvailable(location);
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
            timeouts.pageLoadTimeout(STANDARD_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            
            Common.wait = new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS).pollingEvery(Duration.ofMillis(INTERVALL_IN_MILLISECONDS));
        }
    }
    
    private static void _true(boolean condition)
    {
        Common.equals(true, condition);
    }
    
    private static void _false(boolean condition)
    {
        Common.equals(false, condition);
    }
    
    private static void pressKey(Keys key)
    {
        getVisibleWebElement(ANY_ELEMENT).sendKeys(key);
    }
    
    private static void contains(String containment, String actualValue) 
    {
        if(!actualValue.contains(containment))
        {
            errorMessage(getNotContainsMessage(containment, actualValue));
        }
    }
    
    public static void contains(String expectedValue, List<String> actualValues)
    {
        if(!actualValues.contains(expectedValue))
        {
            errorMessage(getNotContainsMessage(expectedValue, actualValues));
        }
    }
    
    public static void contains(List<String> expectedValues, List<String> actualValues)
    {
        for(String expectedValue : actualValues)
        {
            if(!actualValues.contains(expectedValue))
            {
                errorMessage(getNotContainsMessage(expectedValue, actualValues));
            }
        }
    }
    
    private static <T> void equals(T expectedValue, T actualValue)
    {
        ReflectionAssert.assertReflectionEquals(expectedValue, actualValue);
    }
    
    public static <T> void notEquals(T unexpectedValue, T actualValue)
    {
        Assert.assertNotEquals(unexpectedValue, actualValue);
    }
    
    private static void selectDefaultFrame()
    {
        driver.switchTo().defaultContent();
    }
    
    public static boolean isAvailable(By location)
    {
        return driver.findElements(location).size() != 0;
    }
    
    private static void enabled(By location)
    {
        handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    private static void disabled(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.elementToBeClickable(location)));
    }
    
    private static void checked(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeSelected(location));
    }
    
    private static void unchecked(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.elementToBeSelected(location)));
    }
    
    private static void visitUrl(String url)
    {
        handleException((Consumer<String>)driver::get, Common::getTimeoutMessage, url);
    }
    
    private static void selectFrame(String iframeNameOrId)
    {
        handleException((Function<ExpectedCondition<WebDriver>, WebDriver>)wait::until, Common::getTimeoutMessage, ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeNameOrId));
    }

    private static void chooseDropDownItem(By location, String item)
    {
        Select select =  handleException((WebElement webElement) -> new Select(webElement), Common::getUnexpectedTagNameMessage, Common.getClickableWebElement(location));
        handleException(select::selectByVisibleText, Common::getNoSuchElementMessage, item);
    }
    
    private static WebElement getPresentWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.presenceOfElementLocated(location));
    }
    
    private static WebElement getVisibleWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    private static WebElement getClickableWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    public static void disappeared(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.invisibilityOfElementLocated(location));
    }
    
    private static <T, R> R handleException(Function<T, R> executeFunction, Function<T, String> exceptionMessageFunction, T arg)
    {
        R result = null;
        
        try
        {
            result = executeFunction.apply(arg);
        }
        catch(RuntimeException e)
        {
            errorMessage(exceptionMessageFunction.apply(arg));
        }
        
        return result;
    }
    
    private static <T> void handleException(Consumer<T> executeFunction, Function<T, String> exceptionMessageFunction, T arg)
    {
        try
        {
            executeFunction.accept(arg);
        }
        catch(RuntimeException e)
        {
            errorMessage(exceptionMessageFunction.apply(arg));
        }
    }
    
    private static <T> String getNotContainsMessage(T containmentValue, T actualValue)
    {
        return getMessage(StringCollection.Error.NOT_CONTAINS_HEADER, StringCollection.Identifier.ACTUAL_VALUE + actualValue.toString() + System.lineSeparator() + StringCollection.Identifier.CONTAINMENT_VALUE + containmentValue);
    }
    
    private static <T> String getTimeoutMessage(T arg)
    {
        return getMessage(StringCollection.Error.TIMEOUT_HEADER, arg.toString());
    }
    
    private static <T> String getUnexpectedTagNameMessage(T arg)
    {
        return getMessage(StringCollection.Error.UNEXPECTED_TAG_NAME_HEADER, arg.toString());
    }
    
    private static <T> String getNoSuchElementMessage(T arg)
    {
        return getMessage(StringCollection.Error.NO_SUCH_ELEMENT_HEADER, arg.toString());
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