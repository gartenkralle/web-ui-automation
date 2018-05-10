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
    public void getTable1()
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
    
    @Test
    public void getTable2()
    {
        UserInterface.Action.visitUrl(TABLE_URL);
        
        Table table = UserInterface.DataReceive.getTable(TABLE);
        String actualValue = table.get(3).get(2);
        
        UserInterface.Verify.equals("Austria", actualValue);
    }
    
    @Test
    public void getCount()
    {
        UserInterface.Action.visitUrl(TABLE_URL);
        
        int count = UserInterface.DataReceive.getCount(TABLE);
        
        UserInterface.Verify.equals(1, count);
        
    }
}
