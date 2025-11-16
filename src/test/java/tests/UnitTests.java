package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests {

    @Test
    public void testBasicMath() {
        Assert.assertEquals(2 + 2, 4, "Basic addition should work");
    }

    @Test
    public void testStringOperations() {
        String name = "Jenkins";
        Assert.assertTrue(name.startsWith("Jen"), "String should start with Jen");
    }

    @Test
    public void testBooleanLogic() {
        Assert.assertTrue(5 > 3, "5 should be greater than 3");
    }
}