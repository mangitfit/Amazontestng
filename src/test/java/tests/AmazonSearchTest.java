package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.TestDataProvider;

@Epic("Web E-commerce")
@Feature("Search Functionality")
public class AmazonSearchTest extends BaseTest {

    /**
     * 🧪 TEST 1: Basic Search Functionality
     * @param searchItem - From DataProvider
     *
     * TestNG Annotations Breakdown:
     * @Test - Marks this as a test method
     * dataProvider = "searchItems" - Links to DataProvider method
     * dataProviderClass = TestDataProvider.class - Where to find DataProvider
     * groups = {"smoke", "search"} - Test categorization for selective running
     * priority = 1 - Execution order (lower numbers run first)
     */

    @Test(dataProvider = "searchItems", dataProviderClass = TestDataProvider.class,
            groups = {"smoke", "search"}, priority = 1)
    @Story("User tries to search item")
    @Description("Verifies successful search of item")
    public void testBasicSearchFunctionality(String searchItem){

       //.1 Arrange  -setup
        //testing for searchItem
        System.out.println("Testing for " + searchItem);
        HomePage homePage = new HomePage(driver,wait);

        //2. Act-perform
        //verify homepage is loaded first
        Assert.assertTrue(homePage.isHomePageLoaded() , "Amazon Homepage should be  loaded");

        //3. search for item
        SearchResultsPage resultsPage =  homePage.serachForItem(searchItem);

        //4. verify search results are displayed
        resultsPage.verifysearchResultsAreDisplayed();

        //5.verify search results contain our search term
         boolean containsSearchTerm =     resultsPage.verifySearchResultsContainText(searchItem);

         Assert.assertTrue(containsSearchTerm,"Search results should contain the search term" + searchItem );

        //6. print results for visibility
        resultsPage.printFirstFiveResults();
    }
}
