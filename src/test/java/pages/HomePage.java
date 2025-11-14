package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 🎯 PAGE LOCATORS - Using @FindBy annotation
    // These are the UI elements we interact with

    //page locators
     @FindBy(id ="twotabsearchtextbox")
     private WebElement searchBox;

     @FindBy(css="div[class*='nav-search-submit']")
     private WebElement searchBtn;

     @FindBy(css="div[id='nav-logo']")
     private WebElement amazonLogo;

    /**
     * 🏠 CONSTRUCTOR - Initializes the page
     * @param driver - WebDriver instance from BaseTest
     *
     * What happens here:
     * 1. Store driver reference for this page
     * 2. Initialize WebDriverWait for explicit waits
     * 3. PageFactory.initElements() - Magic that initializes all @FindBy elements
     */

    //constructor
    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    //methods

    /**
     * ⌨️ METHOD: Enter search term
     * @param searchTerm - Text to search for
     *
     * Steps:
     * 1. Wait for search box to be clickable
     * 2. Clear any existing text
     * 3. Type the search term
     */

    public void enterSearchTerm(String searchTerm){
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        System.out.println("entered search term" + searchTerm);
    }

    /**
     * 🔘 METHOD: Click search button
     * @return SearchResultsPage - The next page object
     *
     * This method returns the next page object, enabling method chaining
     */

    //click searchButton method
    public SearchResultsPage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
        System.out.println("clicked searched button");
        return new SearchResultsPage(driver,wait);
    }

    /**
     * 🔍 METHOD: Complete search flow
     * @param searchTerm - Text to search for
     * @return SearchResultsPage - The search results page
     *
     * This combines enterSearchTerm() and clickSearchButton() for convenience
     */

    //combine method of above two - serachForItem
    public SearchResultsPage serachForItem(String searchTerm){
        enterSearchTerm(searchTerm);
        return clickSearchBtn();  //method chaining enabled
    }

    /**
     * ✅ METHOD: Verify home page is loaded
     * @return boolean - true if page is loaded successfully
     *
     * Uses Amazon logo presence to verify page load
     */

    //method - verify HomePage is loaded - check with Amazon logi displayed
    public boolean  isHomePageLoaded(){
        try{
            wait.until(ExpectedConditions.visibilityOf(amazonLogo));
            return amazonLogo.isDisplayed();
        }
        catch(Exception e){
            return false; //Logo not found , page not loaded
        }
    }


}
