package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setupSuite() {
        System.out.println("=== Amazon Search Test Suite Started ===");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run without GUI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
    }

    @BeforeMethod
    public void setupMethod() { // ✅ NO PARAMETERS!
        System.out.println("=== Setting up Chrome browser ===");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.amazon.com");
        System.out.println("✅ Navigated to Amazon homepage");
    }

    @AfterMethod
    public void tearDownMethod() {
        System.out.println("=== Closing browser ===");
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("=== Amazon Search Test Suite Completed ===");
    }
}