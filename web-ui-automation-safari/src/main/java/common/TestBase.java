package common;

import org.junit.After;
import org.junit.Before;

import common.Driver;

public abstract class TestBase
{
    @Before
    public void setup()
    {
        Driver.setup(SafariDriver.getDriver());
    }
    
    @After
    public void quit()
    {
        Driver.quit();
    }
}
