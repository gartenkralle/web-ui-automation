package common;

import org.junit.After;
import org.junit.Before;

public abstract class TestBase
{
    @Before
    public void setup()
    {
        Driver.setup();
    }
    
    @After
    public void tearDown()
    {
        Driver.quit();
    }
}
