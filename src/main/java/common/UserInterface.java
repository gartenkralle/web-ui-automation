package common;

import java.util.List;

import org.openqa.selenium.By;

import common.Table.Row;

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
        
        public static void appeared(By location)
        {
            Common.Verify.visible(location);
        }
        
        public static void disappeared(By location)
        {
            Common.Verify.notVisible(location);
        }
        
        public static void available(By location)
        {
            Common.Verify.present(location);
        }
        
        public static void unavailable(By location)
        {
            Common.Verify.notPresent(location);
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
            Common.Verify.selected(location);
        }
        
        public static void unchecked(By location)
        {
            Common.Verify.unselected(location);
        }
        
        public static void selected(By location)
        {
            Common.Verify.selected(location);
        }
        
        public static void unselected(By location)
        {
            Common.Verify.unselected(location);
        }
        
        public static void url(String url)
        {
            Common.Verify.url(url);
        }
        
        public static <T> void equals(T expected, T actual)
        {
            Common.Verify.equals(expected, actual);
        }
        
        public static <T> void notEquals(T unexpected, T actual)
        {
            Common.Verify.notEquals(unexpected, actual);
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
        
        public static void appearedCount(By location, int expected)
        {
            Common.Verify.visibleCount(location, expected);
        }
        
        public static void availableCount(By location, int expected)
        {
            Common.Verify.presentCount(location, expected);
        }
        
        public static void notEmpty(List<String> values)
        {
            Common.Verify.notEmpty(values);
        }
        
        public static void _true(boolean condition)
        {
            Common.Verify._true(condition);
        }
        
        public static void _false(boolean condition)
        {
            Common.Verify._false(condition);
        }
        
        public static void table(By actualTableLocation, Table expectedTable)
        {
            Common.Verify.table(actualTableLocation, expectedTable);
        }
        
        public static void tableRow(By actualTable, Row expectedRow)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void tableRow(By actualTable, Row expectedRow, int row)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void tableCell(By actualTable, String text, int row, int col)
        {
            throw new UnsupportedOperationException();
        }
        
        public static void tooltip(By actualTooltipLocation, String expectedTooltip)
        {
            throw new UnsupportedOperationException();
        }
    }
    
    public static class DataReceive
    {
        private DataReceive()
        {
            
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
            return Common.DataReceive.getValue(location, attributeName);
        }
        
        public static List<String> getAllValue(By location, String attributeName)
        {
            return Common.DataReceive.getAllValue(location, attributeName);
        }
        
        public static int getAppearedCount(By location)
        {
            return Common.DataReceive.getVisibleCount(location);
        }
        
        public static int getAvailableCount(By location)
        {
            return Common.DataReceive.getPresentCount(location);
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
