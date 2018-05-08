package common;

import java.util.List;

import org.openqa.selenium.By;

public final class UserInterface
{
    private UserInterface()
    {
        
    }
    
    public static class Action
    {
        private Action()
        {
            
        }
        
        public static <T> void fillField(By location, T value)
        {
            Common.Action.fillField(location, value);
        }
        
        public static void clickElement(By location)
        {
            Common.Action.clickElement(location);
        }
        
        public static void clickRadioButton(By location)
        {
            Common.Action.clickRadioButton(location);
        }
        
        public static void checkCheckbox(By location)
        {
            Common.Action.checkCheckbox(location);
        }
        
        public static void uncheckCheckbox(By location)
        {
            Common.Action.uncheckCheckbox(location);
        }
        
        public static void chooseDropDownItem(By location, String item)
        {
            Common.Action.chooseDropDownItem(location, item);
        }
        
        public static void moveSlider(By location, int pixel) 
        {
            throw new UnsupportedOperationException();
        }
        
        public static void moveSlider(By location, float percent) 
        {
            throw new UnsupportedOperationException();
        }
        
        public static void mouseOver(By location)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void pressEnter()
        {
            Common.Action.pressEnter();
        }
        
        public static void pressEscape()
        {
            Common.Action.pressEscape();
        }
        
        public static void selectDefaultFrame()
        {
            Common.Action.selectDefaultFrame();
        }
        
        public static void selectFrame(String iframeNameOrId)
        {
            Common.Action.selectFrame(iframeNameOrId);
        }
        
        public static void visitUrl(String url)
        {
            Common.Action.visitUrl(url);
        }
    }
    
    public static class Verify
    {
        private Verify()
        {
            
        }
        
        public static void appeared(By location)
        {
            Common.Verify.appeared(location);
        }
        
        public static void available(By location)
        {
            Common.Verify.available(location);
        }
        
        public static void disappeared(By location)
        {
            Common.Verify.disappeared(location);
        }
        
        public static void enabled(By location)
        {
            Common.Verify.enabled(location);
        }
        
        public static void disabled(By location)
        {
            Common.Verify.disabled(location);
        }
        
        public static void checked(By location)
        {
            Common.Verify.checked(location);
        }
        
        public static void unchecked(By location)
        {
            Common.Verify.unchecked(location);
        }
        
        public static void url(String url)
        {
            Common.Verify.url(url);
        }
        
        public static <T> void equals(T expectedValue, T actualValue)
        {
            Common.Verify.equals(expectedValue, actualValue);
        }
        
        public static <T> void notEquals(T expectedValue, T actualValue)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void contains(String containment, String actualValue)
        {
            Common.Verify.contains(containment, actualValue);
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
            Common.Verify._true(condition);
        }
        
        public static void _false(boolean condition)
        {
            Common.Verify._false(condition);
        }
    }
    
    public static class DataReceive
    {
        private DataReceive()
        {
            
        }
        
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
            return Common.DataReceive.isEnabled(location);
        }
        
        public static boolean isDisabled(By location)
        {
            return Common.DataReceive.isDisabled(location);
        }
        
        public static boolean isChecked(By location)
        {
            return Common.DataReceive.isChecked(location);
        }
        
        public static boolean isUnchecked(By location)
        {
            return Common.DataReceive.isUnchecked(location);
        }
    }
    
    public static class Setting
    {
        private Setting()
        {
            
        }
        
        public static void setTimeout(int seconds)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void resetTimeout()
        {
            throw new UnsupportedOperationException();
        }
    }
}
