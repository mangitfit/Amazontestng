package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    /**
     * 📁 DATA PROVIDER: Basic search items
     * @return Object[][] - 2D array of test data
     *
     * Structure: Each inner array represents one test case
     * Format: { "search_term" }
     */

    @DataProvider(name="searchItems")
    public Object[][] provideSearchItems(){
        return new Object[][] {
                {"laptop"},
//                {"smartphone"},
//                {"books"},
//                {"watches"},
//                {"headphones"},
//                {"shoes"}
        };
        // 🚀 TestNG will run the test method 6 times with different data
    }

    /**
     * 📁 DATA PROVIDER: Detailed search with multiple parameters
     * @return Object[][] - 2D array with multiple data points per test
     *
     * Format: { "search_term", "category", expected_min_results }
     */

    @DataProvider(name="detailedSearchItems")
    public Object[][] provideDetailedSearchItems(){
        return new Object [][]{
                {"macbookpro","electronics",5},
                {"iphone17","smartphone",3},
                {"Growth mindset", "books",2 },
                {"casio", "watches", 6},
                {"Boat", "headphones", 2},
                {"Adidas", "shoes", 1}
        };
    }
}
