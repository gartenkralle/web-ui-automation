package common;

import java.time.Duration;
import java.util.ArrayList;
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

import common.Table.Row;

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
            if(isUnchecked(location))
            {
                getClickableWebElement(location).click();
            }
        }
        
        public static void uncheckCheckbox(By location)
        {
            if(isChecked(location))
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
        
        public static void notVisible(By location)
        {
            Common.invisible(location);
        }
        
        public static void present(By location)
        {
            Common.getPresentWebElement(location);
        }
        
        public static void notPresent(By location)
        {
            Common.notPresent(location);
        }
        
        public static void url(String url)
        {
            Common.equals(url, driver.getCurrentUrl());
        }
        
        public static void selected(By location)
        {
            Common.selected(location);
        }
        
        public static void unselected(By location)
        {
            Common.unselected(location);
        }
        
        public static void enabled(By location)
        {
            Common.clickable(location);
        }
        
        public static void disabled(By location)
        {
            Common.notClickable(location);
        }
        
        public static void visibleCount(By location, int expected)
        {
            Common.visibleCount(location, expected);
        }
        
        public static void presentCount(By location, int expected)
        {
            Common.presentCount(location, expected);
        }
        
        public static void table(By actualTableLocation, Table expectedTable)
        {
            Common.table(actualTableLocation, expectedTable);
        }
        
        public static void tableRow(By actualTable, Row expectedRow)
        {
            Common.tableRow(actualTable, expectedRow);
        }
        
        public static void text(By location, String expectedText)
        {
            Common.text(location, expectedText);
        }
        
        public static void attribute(By location, String expectedAttribute)
        {
            Common.attribute(getPresentWebElement(location), expectedAttribute);
        }
        
        public static void attributeValue(By location, String attributeName, String expectedValue)
        {
            Common.attributeValue(location, attributeName, expectedValue);
        }
        
        public static void selected(By dropdownMenu, String text)
        {
            Common.selected(dropdownMenu, text);
        }
    }
    
    public static class DataReceive
    {
        private DataReceive()
        {
            
        }
        
        public static int getVisibleCount(By location)
        {
            return Common.getVisibleCount(location);
        }
        
        public static int getPresentCount(By location)
        {
            return Common.getPresentCount(location);
        }
        
        public static String getText(By location)
        {
            return Common.getText(location);
        }
        
        public static Table getTable(By location)
        {
            return Common.getTable(location);
        }
        
        public static List<String> getTexts(By location)
        {
            return Common.getTexts(location);
        }
        
        public static String getAttributeValue(By location, String attributeName)
        {
            return Common.getAttributeValue(location, attributeName);
        }
        
        public static List<String> getAttributeValues(By location, String attributeName)
        {
            return Common.getAttributeValues(location, attributeName);
        }
        
        public static boolean isAppeared(By location)
        {
            return getVisibleWebElement(location).isDisplayed();
        }
        
        public static boolean isDisappeared(By location)
        {
            return !isAppeared(location);
        }
        
        public static Row getTableRow(By table, int rowIndex)
        {
            return getTable(table).get(rowIndex);
        }
        
        public static boolean isEnabled(By location)
        {
            return getVisibleWebElement(location).isEnabled();
        }
        
        public static boolean isDisabled(By location)
        {
            return !isEnabled(location);
        }
        
        public static boolean isAvailable(By location)
        {
            return getPresentCount(location) != 0;
        }
        
        public static boolean isUnavailable(By location)
        {
            return !isAvailable(location);
        }
        
        public static String getUrl()
        {
            return driver.getCurrentUrl();
        }
        
        public static boolean isSelected(By location)
        {
            return Common.getVisibleWebElement(location).isSelected();
        }
        
        public static boolean isUnselected(By location)
        {
            return !isSelected(location);
        }
        
        public static boolean isChecked(By location)
        {
            return isSelected(location);
        }
        
        public static boolean isUnchecked(By location)
        {
            return !isSelected(location);
        }
        
        public static String getSelection(By dropdownMenu)
        {
            return Common.getSelection(dropdownMenu);
        }
    }
    
    public static class Compare
    {
        private Compare()
        {
            
        }
        
        public static void _true(boolean condition)
        {
            Common._true(condition);
        }
        
        public static void _false(boolean condition)
        {
            Common._false(condition);
        }
        
        public static <T> void empty(List<T> values)
        {
            Common.empty(values);
        }
        
        public static <T> void notEmpty(List<T> values)
        {
            Common.notEmpty(values);
        }
        
        public static void contains(String expectedValue, List<String> actualValues)
        {
            Common.contains(expectedValue, actualValues);
        }
        
        public static void contains(List<String> expectedValues, List<String> actualValues)
        {
            Common.contains(expectedValues, actualValues);
        }
        
        public static void contains(String containmentValue, String actualValue)
        {
            Common.contains(containmentValue, actualValue);
        }
        
        public static <T> void equals(T expected, T actual)
        {
            Common.equals(expected, actual);
        }
        
        public static <T> void notEquals(T unexpected, T actual)
        {
            Common.notEquals(unexpected, actual);
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
    
    private static String getSelection(By dropdownMenu)
    {
        return new Select(getVisibleWebElement(dropdownMenu)).getFirstSelectedOption().getText();
    }
    
    private static boolean isEquals(By location, String text)
    {
        return getSelection(location).equals(text);
    }
    private static boolean isChecked(By location)
    {
        return Common.getVisibleWebElement(location).isSelected();
    }
    
    private static boolean isUnchecked(By location)
    {
        return !Common.getVisibleWebElement(location).isSelected();
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
        Common.driver.manage().deleteAllCookies();
        
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
    
    private static <T> void empty(List<T> values)
    {
        equals(0, values.size());
    }
    
    private static <T> void notEmpty(List<T> values)
    {
        notEquals(0, values.size());
    }
    
    private static <T> void notEquals(T unexpectedValue, T actualValue)
    {
        Assert.assertNotEquals(unexpectedValue, actualValue);
    }
    
    private static void selectDefaultFrame()
    {
        driver.switchTo().defaultContent();
    }
    
    private static String getText(By location)
    {
        return getText(getVisibleWebElement(location));
    }
    
    private static List<String> getTexts(By location)
    {
        List<String> results = new ArrayList<>();
        
        for(WebElement webElement : getVisibleWebElements(location))
        {
            results.add(getText(webElement));
        }
        
        return results;
    }
    
    private static String getText(WebElement webElement)
    {
        String result = null;
        
        String inputType = webElement.getTagName();
        
        switch(inputType)
        {
            case StringCollection.ControlType.INPUT:
            case StringCollection.ControlType.TEXTAREA:
                result = webElement.getAttribute(StringCollection.AttributeType.VALUE);
            break;
            
            case StringCollection.ControlType.SELECT:
                result = new Select(webElement).getFirstSelectedOption().getText();
            break;
            
            default:
                result = webElement.getText();
            break;
        }
        
        return result;
    }
    
    private static String getAttributeValue(By location, String attributeName)
    {
        return getAttributeValue(getPresentWebElement(location), attributeName);
    }
    
    private static List<String> getAttributeValues(By location, String attributeName)
    {
        List<String> results = new ArrayList<>();
        
        for(WebElement webElement : getPresentWebElements(location))
        {
            results.add(getAttributeValue(webElement, attributeName));
        }
        
        return results;
    }
    
    private static String getAttributeValue(WebElement webElement, String attributeName)
    {
        return webElement.getAttribute(attributeName);
    }
    
    private static void visibleCount(By location, int expected)
    {
        equals(expected, getVisibleCount(location));
    }
    
    private static int getVisibleCount(By location)
    { 
        return getVisibleWebElements(location).size();
    }
    
    private static void presentCount(By location, int expected)
    {
        equals(expected, getPresentCount(location));
    }
    
    private static int getPresentCount(By location)
    { 
        return getPresentWebElements(location).size();
    }
    
    private static Table getTable(By tableLocation)
    {
        Table table = new Table();
        
        WebElement tableWebElement = getVisibleWebElement(tableLocation);
        List<WebElement> rowWebElements = tableWebElement.findElements(By.xpath(StringCollection.XPath.ROW_SELECTOR_INSIDE));
        
        for(WebElement rowWebElement : rowWebElements)
        {
            table.add(getRow(rowWebElement));
        }
        
        return table;
    }
    
    private static Row getRow(WebElement rowWebElement)
    {
        Row row = new Row();
        
        List<WebElement> colWebElements = rowWebElement.findElements(By.xpath(StringCollection.XPath.ANY_ELEMENT_INSIDE));
        
        for(WebElement colWebElement : colWebElements)
        {
            row.add(getCol(colWebElement));
        }
        
        return row;
    }
    
    private static String getCol(WebElement colWebElement)
    {
        return colWebElement.getText();
    }
    
    private static void table(By actualTableLocation, Table expectedTable)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, tableToBe(actualTableLocation, expectedTable));
    }
    
    private static void tableRow(By actualTableLocation, Row expectedRow)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, tableRowToBe(actualTableLocation, expectedRow));
    }
    
    private static boolean isEquals(By actualTableLocation, Table expectedTable)
    {
        return getTable(actualTableLocation).equals(expectedTable);
    }
    
    private static boolean isEquals(By actualTableLocation, Row expectedRow)
    {
        Table actualTable = getTable(actualTableLocation);
        
        for(Row row : actualTable)
        {
            if(row.equals(expectedRow))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private static ExpectedCondition<Boolean> selectionToBe(final By actualDropdownLocation, final String expectedText)
    {
        return new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                return isEquals(actualDropdownLocation, expectedText);
            }
            
            @Override
            public String toString()
            {
                return System.lineSeparator() + StringCollection.Identifier.ACTUAL_VALUE + System.lineSeparator() + getSelection(actualDropdownLocation) +
                       System.lineSeparator() + StringCollection.Identifier.EXPECTED_VALUE + System.lineSeparator() + expectedText;
            }
        };
    }
    
    private static ExpectedCondition<Boolean> tableToBe(final By actualTableLocation, final Table expectedTable)
    {
        return new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                return isEquals(actualTableLocation, expectedTable);
            }
            
            @Override
            public String toString()
            {
                Table actualTable = getTable(actualTableLocation);
                
                return System.lineSeparator() + StringCollection.Identifier.ACTUAL_TABLE_HEADER + System.lineSeparator() + actualTable.toString() +
                       System.lineSeparator() + StringCollection.Identifier.EXPECTED_TABLE_HEADER + System.lineSeparator() + expectedTable.toString();
            }
        };
    }
    
    private static ExpectedCondition<Boolean> tableRowToBe(final By actualTableLocation, final Row expectedRow)
    {
        return new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                return isEquals(actualTableLocation, expectedRow);
            }
            
            @Override
            public String toString()
            {
                Table actualTable = getTable(actualTableLocation);
                
                return System.lineSeparator() + StringCollection.Identifier.ACTUAL_TABLE_HEADER + System.lineSeparator() + actualTable.toString() +
                       System.lineSeparator() + StringCollection.Identifier.EXPECTED_ROW_HEADER + System.lineSeparator() + expectedRow.toString();
            }
        };
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
    
    private static void clickable(By location)
    {
        handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
    }
    
    private static void notClickable(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.elementToBeClickable(location)));
    }
    
    private static void selected(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeSelected(location));
    }
    
    private static void selected(By location, String text)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, selectionToBe(location, text));
    }
    
    private static void unselected(By location)
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
    
    private static void text(By location, String expectedText)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.textToBe(location, expectedText));
    }
    
    private static void attributeValue(By location, String attributeName, String expectedValue)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.attributeContains(location, attributeName, expectedValue));
    }
    
    private static void attribute(WebElement webElement, String expectedAttribute)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.attributeToBeNotEmpty(webElement, expectedAttribute));
    }
    
    private static void notPresent(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(location)));
    }
    
    private static List<WebElement> getPresentWebElements(By location)
    {
        return handleException((Function<ExpectedCondition<List<WebElement>>, List<WebElement>>)wait::until, Common::getTimeoutMessage, ExpectedConditions.presenceOfAllElementsLocatedBy(location));
    }
    
    private static WebElement getVisibleWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.visibilityOfElementLocated(location));
    }
    
    private static void invisible(By location)
    {
        handleException((Function<ExpectedCondition<Boolean>, Boolean>)wait::until, Common::getTimeoutMessage, ExpectedConditions.invisibilityOfElementLocated(location));
    }
    
    private static List<WebElement> getVisibleWebElements(By location)
    {
        return handleException((Function<ExpectedCondition<List<WebElement>>, List<WebElement>>)wait::until, Common::getTimeoutMessage, ExpectedConditions.visibilityOfAllElementsLocatedBy(location));
    }
    
    private static WebElement getClickableWebElement(By location)
    {
        return handleException((Function<ExpectedCondition<WebElement>, WebElement>)wait::until, Common::getTimeoutMessage, ExpectedConditions.elementToBeClickable(location));
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