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
import org.openqa.selenium.interactions.Actions;
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
        
        public static void selectFrame(String iFrameNameOrId)
        {
            Common.selectFrame(iFrameNameOrId);
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

        public static void moveSlider(By location, int pixel)
        {
            Common.moveSlider(location, pixel);
        }

        public static void mouseOver(By location)
        {
            Common.mouseOver(location);
        }

        public static void moveSlider(By location, float percent)
        {
            Common.moveSlider(location, percent);
        }
    }
    
    public static class Verify
    {
        private Verify()
        {
            
        }
        
        public static void visible(By location)
        {
            Common.getVisibleWebElement(location);
        }
        
        public static void present(By location)
        {
            Common.getPresentWebElement(location);
        }
        
        public static void invisible(By location)
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

        public static void count(By location, int expected)
        {
            Common.count(location, expected);
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
        
        public static int getCount(By location)
        {
            return Common.getCount(location);
        }

        public static String getText(By location)
        {
            return Common.getText(location);
        }
    }
    
    public static class Setting
    {
        private Setting()
        {
            
        }
        
        public static void setup(WebDriver driver)
        {
            Common.setup(driver);
        }

        public static void setTimeout(int seconds)
        {
            Common.setTimeout(seconds);
        }

        public static void resetTimeout()
        {
            Common.resetTimeout();
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
    
    private static void setup(WebDriver driver)
    {
        Common.driver = driver;
        Common.wait = new WebDriverWait(driver, 0);
        
        setTimeout(Common.STANDARD_TIMEOUT_IN_SECONDS);
    }
    
    private static void setTimeout(int seconds)
    {
        Common.driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
        Common.wait.withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(Common.INTERVALL_IN_MILLISECONDS));
    }
    
    private static void resetTimeout()
    {
        setTimeout(Common.STANDARD_TIMEOUT_IN_SECONDS);
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
    
    private static void contains(String expectedValue, List<String> actualValues)
    {
        if(!actualValues.contains(expectedValue))
        {
            errorMessage(getNotContainsMessage(expectedValue, actualValues));
        }
    }
    
    private static void contains(List<String> expectedValues, List<String> actualValues)
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
    
    private static <T> void notEquals(T unexpectedValue, T actualValue)
    {
        Assert.assertNotEquals(unexpectedValue, actualValue);
    }
    
    private static void selectDefaultFrame()
    {
        driver.switchTo().defaultContent();
    }
    
    private static boolean isAvailable(By location)
    {
        return getCount(location) != 0;
    }
    
    private static String getText(By location)
    {
        String result = null;
        
        String inputType = getPresentWebElement(location).getTagName();
        
        switch(inputType)
        {
            case StringCollection.ControlType.INPUT:
            case StringCollection.ControlType.TEXTAREA:
                result = getVisibleWebElement(location).getAttribute(StringCollection.AttributeType.VALUE);
            break;
            
            case StringCollection.ControlType.SELECT:
                result = new Select(getVisibleWebElement(location)).getFirstSelectedOption().getText();
            break;
            
            default:
                result = getVisibleWebElement(location).getText();
            break;
        }
        
        return result;
    }
    
    private static void count(By location, int expected)
    {
        equals(expected, getCount(location));
    }
    
    private static int getCount(By location)
    { 
        return driver.findElements(location).size();
    }
    
    private static void moveSlider(By location, int pixel)
    {
        new Actions(driver).clickAndHold(getVisibleWebElement(location)).moveByOffset(pixel, 0).release().perform();
    }
    
    private static void moveSlider(By location, float percent)
    {
        int pixel = Math.round((((float)getVisibleWebElement(location).getSize().width / 100) * percent));
        
        moveSlider(location, pixel);
    }
    
    private static void mouseOver(By location)
    {
        new Actions(driver).moveToElement(driver.findElement(location)).build().perform();
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
    
    private static void selectFrame(String iFrameNameOrId)
    {
        handleException((Function<ExpectedCondition<WebDriver>, WebDriver>)wait::until, Common::getTimeoutMessage, ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameNameOrId));
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
    
    private static void disappeared(By location)
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