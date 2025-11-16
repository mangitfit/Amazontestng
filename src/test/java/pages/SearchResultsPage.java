package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    //Search results page locator

    @FindBy(css="div[data-component-type='s-search-result']")
    private List<WebElement> searchResults;

    @FindBy(css="div[data-component-type='s-search-result'] h2")
    private List<WebElement> productTitles;

   //constructor

    public SearchResultsPage(WebDriver driver, WebDriverWait wait){
        this.driver= driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    public int getSearchResultsCount(){
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return searchResults.size();
    }

//    /**
//     * 🔍 METHOD: Verify results contain expected text
//     * @param expectedText - Text that should appear in results
//     * @return boolean - true if text found in any result
//     *
//     * Checks both title selectors since Amazon UI can vary
//     */

//    public boolean verifySearchResultsContainText(String expectedText) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
//
//        for (WebElement title : productTitles) {
//            if (title.getText().toLowerCase().contains(expectedText.toLowerCase()))
//                return true;
//        }
//
//        return false;
//    }

    //verify search results are displayed
    public void verifysearchResultsAreDisplayed(){
       wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
            Assert.assertTrue(searchResults.size()>0, "No search results displayed");
        System.out.println("found" + searchResults.size() + "search results");
    }

    /**
     * 📝 METHOD: Print first few results for debugging
     * Helpful for verifying search is working correctly
     */

//    public void printFirstFiveResults(){
//
//        int count = Math.min(productTitles.size(),5);
//
//        for(int i=0;i<count;i++){
//          String title =   productTitles.size()>i ? productTitles.get(i).getText() :"N/A";
//
//          System.out.println((i+1) + "." + title);
//        }
//    }






}
