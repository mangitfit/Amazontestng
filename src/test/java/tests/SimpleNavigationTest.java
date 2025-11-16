//package tests;
//
//import base.BaseTest;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class SimpleNavigationTest extends BaseTest {
//
//    @Test
//    public void testSimpleNavigation() {
//        System.out.println("Current URL: " + driver.getCurrentUrl());
//        System.out.println("Page Title: " + driver.getTitle());
//        Assert.assertTrue(driver.getTitle().contains("Amazon"), "Should be on Amazon page");
//    }
//
//    // You can add more simple tests here
//    @Test
//    public void testBrowserIsWorking() {
//        String title = driver.getTitle();
//        System.out.println("Browser is working. Page title: " + title);
//        Assert.assertNotNull(title, "Page title should not be null");
//        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
//
//    }
//}
