package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import common.TestBase;
import common.UserInterface;

public class General extends TestBase
{
    private final static String GENERAL_URL = "https://www.w3schools.com/html/default.asp";
    
    private final static By HTML5_HEADER = By.xpath("//*[@id='main']/h1");
    
    @Test
    public void getValue()
    {
        UserInterface.Action.visitUrl(GENERAL_URL);
        
        String header = UserInterface.DataReceive.getText(HTML5_HEADER);
        
        UserInterface.Verify.equals("HTML5 Tutorial", header);
    }
}
