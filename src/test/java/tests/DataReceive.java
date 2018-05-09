package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import common.Table;
import common.TestBase;
import common.UserInterface;

public class DataReceive extends TestBase
{
    private final static String TABLE_URL = "https://www.w3schools.com/html/html_tables.asp";
    
    private final static By TABLE = By.xpath("//*[@id='customers']");
    
    @Test
    public void getTable()
    {
        UserInterface.Action.visitUrl(TABLE_URL);
        
        Table table = UserInterface.DataReceive.getTable(TABLE);
        
        
    }
}
