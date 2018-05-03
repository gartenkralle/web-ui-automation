package common;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class UserInterface
{
    private static WebDriver driver;
    private static WebDriverWait wait;
    
    private UserInterface()
    {
        
    }
    
    public static class Action
    {
        public static <T> void fillField(By location, T value)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void clickElement(By location)
        {
            wait.until(ExpectedConditions.elementToBeClickable(location)).click();
        }
        
        public static void clickElement(By location, String linkText)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void clickRadioButton(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void checkCheckbox(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void chooseDropDownItem(By location, String item)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void uncheckCheckbox(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void moveSlider(By location, int pixel) 
        {
            throw new UnsupportedOperationException();
        }
        
        public static void moveMouse(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void openNewTab()
        {
            throw new UnsupportedOperationException();
        }
        
        public static void pressEnter(By location)
        {
            Helper.pressKey(location, Keys.ENTER);
        }
        
        public static void pressEscape(By location)
        {
            Helper.pressKey(location, Keys.ESCAPE);
        }
        
        public static void reloadPage()
        {
            throw new UnsupportedOperationException();
        }
        
        public static void visitUrl(String url)
        {
            try
            {
                driver.get(url);
            }
            catch(TimeoutException ex)
            {
                Assert.fail();
            }
        }
    }
    
    public static class Verify
    {
        public static void appeared(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void appeared(By location, String value)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void appeared(By location, List<String> values)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void available(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void available(By location, String expectedValue)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void disappeared(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void disappeared(By location, String value)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void enabled(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void disabled(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void checked(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void unchecked(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void url(String url)
        {
            throw new UnsupportedOperationException();
        }
        
        public static <T> void equals(T expectedValue, T actualValue)
        {
            throw new UnsupportedOperationException();
        }
        
        public static <T> void notEquals(T expectedValue, T actualValue)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void contains(String containment, String baseString)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void contains(String expectedValue, List<String> actualValues)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void contains(List<String> expectedValues, List<String> actualValues)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void count(By location, int count)
        {
            throw new UnsupportedOperationException();
        }
        
        public static <T> void notNull(T o)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void notEmpty(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void notEmpty(List<String> values)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void _true(boolean condition)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void _false(boolean condition)
        {
            throw new UnsupportedOperationException();
        }
    }
    
    public static class DataReceive
    {
        public static int getCount(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static Table getTable(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static String getValue(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static String getValue(By location, String attribute)
        {
            throw new UnsupportedOperationException();
        }
        
        public static List<String> getAllValue(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static List<String> getAllValue(By location, String attribute)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isAvailable(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isEnabled(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isDisabled(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isChecked(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isUnchecked(By location)
        {
            throw new UnsupportedOperationException();
        }
    }
    
    public static class Setting
    {
        public static void setTimeout(int seconds)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void resetTimeout()
        {
            throw new UnsupportedOperationException();
        }
        
        public static void setup(WebDriver driver, WebDriverWait wait)
        {
            UserInterface.driver = driver;
            UserInterface.wait = wait;
        }
    }
    
    private static class Helper
    {
        public static void pressKey(By location, Keys enter)
        {
            throw new UnsupportedOperationException();
        }
    }
}
