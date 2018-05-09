package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import common.Table;
import common.Table.Row;
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
        
        Table actualTable = UserInterface.DataReceive.getTable(TABLE);
        
        Table expectedTable = new Table();
        Row row;
        
        row = new Row();
        row.add("Company");
        row.add("Contact");
        row.add("Country");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Alfreds Futterkiste");
        row.add("Maria Anders");
        row.add("Germany");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Centro comercial Moctezuma");
        row.add("Francisco Chang");
        row.add("Mexico");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Ernst Handel");
        row.add("Roland Mendel");
        row.add("Austria");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Island Trading");
        row.add("Helen Bennett");
        row.add("UK");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Laughing Bacchus Winecellars");
        row.add("Yoshi Tannamuri");
        row.add("Canada");
        expectedTable.add(row);
        
        row = new Row();
        row.add("Magazzini Alimentari Riuniti");
        row.add("Giovanni Rovelli");
        row.add("Italy");
        expectedTable.add(row);
        
        UserInterface.Verify.equals(expectedTable, actualTable);
    }
}
