package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import common.TestBase;
import common.UserInterface;

public class Checkbox extends TestBase
{
    private final static String CHECKBOXBUTTON_URL = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_Checkbox";
    private final static String RESULT_IFRAME = "iframeResult";
    
    private final static By CHECKBOX = By.xpath("/html/body/form/input[2]");
    
    @Test
    public void clickCheckbox()
    {
        UserInterface.Action.visitUrl(CHECKBOXBUTTON_URL);
        UserInterface.Action.selectFrame(RESULT_IFRAME);
        
        
        UserInterface.Action.checkCheckbox(CHECKBOX);
        
        UserInterface.Verify.checked(CHECKBOX);
        
        UserInterface.Verify._true(UserInterface.DataReceive.isChecked(CHECKBOX));
        UserInterface.Verify._false(UserInterface.DataReceive.isUnchecked(CHECKBOX));
        
        
        UserInterface.Action.uncheckCheckbox(CHECKBOX);
        
        UserInterface.Verify.unchecked(CHECKBOX);
        
        UserInterface.Verify._true(UserInterface.DataReceive.isUnchecked(CHECKBOX));
        UserInterface.Verify._false(UserInterface.DataReceive.isChecked(CHECKBOX));
    }
}
