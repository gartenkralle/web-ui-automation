package common;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Table.Row;

final class Wait
{
    private final static int INTERVALL_IN_MILLISECONDS = 200;
    
    private static FluentWait<WebDriver> wait;
    
    public static void setup(WebDriver driver)
    {
        wait = new WebDriverWait(driver, 0);
    }
    
    public static void setTimeout(int seconds)
    {
        wait.withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(INTERVALL_IN_MILLISECONDS));
    }
    
    public static WebElement getPresentWebElement(By location)
    {
        return ExceptionHandler.apply((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.presenceOfElementLocated(location));
    }
    
    public static List<WebElement> getPresentWebElements(By location)
    {
        return ExceptionHandler.apply((Function<ExpectedCondition<List<WebElement>>, List<WebElement>>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.presenceOfAllElementsLocatedBy(location));
    }
    
    public static WebElement getVisibleWebElement(By location)
    {
        return ExceptionHandler.apply((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    public static List<WebElement> getVisibleWebElements(By location)
    {
        return ExceptionHandler.apply((Function<ExpectedCondition<List<WebElement>>, List<WebElement>>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.visibilityOfAllElementsLocatedBy(location));
    }
    
    public static WebElement getClickableWebElement(By location)
    {
        return ExceptionHandler.apply((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    public static void frameToBeAvailableAndSwitchToIt(String iFrameNameOrId)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<WebDriver>, WebDriver>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameNameOrId));
    }
    
    public static void attributeContains(By location, String attributeName, String expectedValue)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.attributeContains(location, attributeName, expectedValue));
    }
    
    public static void selectionToBe(By dropdownMenuLocation, String text)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, common.ExpectedConditions.selectionToBe(dropdownMenuLocation, text));
    }
    
    public static void elementToBeSelected(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.elementToBeSelected(location));
    }
    
    public static void invisibilityOfElementLocated(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.invisibilityOfElementLocated(location));
    }
    
    public static void presenceOfElementLocated(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(location)));
    }
    
    public static void notElementToBeSelected(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.elementToBeSelected(location)));
    }
    
    public static void elementToBeClickable(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    public static void notElementToBeClickable(By location)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.elementToBeClickable(location)));
    }
    
    public static void tableToBe(By actualTableLocation, Table expectedTable)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, common.ExpectedConditions.tableToBe(actualTableLocation, expectedTable));
    }
    
    public static void tableRowToBe(By actualTableLocation, Row expectedRow)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, common.ExpectedConditions.tableRowToBe(actualTableLocation, expectedRow));
    }
    
    public static void textToBe(By location, String expectedText)
    {
        ExceptionHandler.apply((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, ExceptionHandler::getTimeoutMessage, ExpectedConditions.textToBe(location, expectedText));
    }
}
