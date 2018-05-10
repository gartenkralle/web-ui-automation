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
            Common.Action.moveSlider(location, pixel);
        }
        
        public static void moveSlider(By location, float percent) 
        {
            Common.Action.moveSlider(location, percent);
        }
        
        public static void mouseOver(By location)
        {
            Common.Action.mouseOver(location);
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
        
        public static void selectFrame(String iFrameNameOrId)
        {
            Common.Action.selectFrame(iFrameNameOrId);
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
        
        public static void visible(By location)
        {
            Common.Verify.visible(location);
        }
        
        public static void present(By location)
        {
            Common.Verify.present(location);
        }
        
        public static void invisible(By location)
        {
            Common.Verify.invisible(location);
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
        
        public static <T> void notEquals(T unexpectedValue, T actualValue)
        {
            Common.Verify.notEquals(unexpectedValue, actualValue);
        }
        
        public static void contains(String containment, String actualValue)
        {
            Common.Verify.contains(containment, actualValue);
        }
        
        public static void contains(String expectedValue, List<String> actualValues)
        {
            Common.Verify.contains(expectedValue, actualValues);
        }
        
        public static void contains(List<String> expectedValues, List<String> actualValues)
        {
            Common.Verify.contains(expectedValues, actualValues);
        }
        
        public static void count(By location, int expected)
        {
            Common.Verify.count(location, expected);
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
            return Common.DataReceive.getCount(location);
        }
        
        public static Table getTable(By location)
        {
            return Common.DataReceive.getTable(location);
        }
        
        public static String getText(By location)
        {
            return Common.DataReceive.getText(location);
        }
        
        public static List<String> getAllText(By location)
        {
            return Common.DataReceive.getAllText(location);
        }
        
        public static String getValue(By location, String attributeName)
        {
            throw new UnsupportedOperationException();
        }
        
        public static List<String> getAllValue(By location, String attributeName)
        {
            throw new UnsupportedOperationException();
        }
        
        public static boolean isPresent(By location)
        {
            return Common.DataReceive.isPresent(location);
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
            Common.Setting.setTimeout(seconds);
        }
        
        public static void resetTimeout()
        {
            Common.Setting.resetTimeout();
        }
    }
}
