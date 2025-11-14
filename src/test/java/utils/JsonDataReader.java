package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class to read JSON data from external files using org.json library.
 * This class demonstrates reading a JSONArray and extracting specific test data
 * based on the structure defined in TestData.json.
 */
public class JsonDataReader {

    private static JSONArray testDataArray;

    /**
     * Initialize the data reader by loading the data file content'
     */

    public JsonDataReader() {
        if (testDataArray == null) {
            loadTestData("src/test/resources/testdata.json");
        }
    }

    /**
     * Reads the content of the specified JSON file into a String.
     *
     * @param filePath The path to the JSON file.
     * @return The file content as a String.
     */
    private String readJsonFile(String filePath) {

        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file at :" + filePath);
            e.printStackTrace();
            Assert.fail("failed to load test data file");
            return null;  //should not reach here
        }
    }

    /**
     * Loads the test data from the file path into a static JSONArray.
     * @param filePath The path to the JSON test data file.
     */
    private void loadTestData(String filePath){
        String  jsonContent =    readJsonFile(filePath);
        if(jsonContent!=null){
              testDataArray = new JSONArray(jsonContent);
              System.out.println("Test data loaded succesfully. Total scenarios " + testDataArray.length());
        }
    }

    /**
     * Finds a specific test case data object by its ID.
     * @param testCaseId The unique identifier of the test case.
     * @return The JSONObject containing the test case data (requestBody, expectedResponse).
     */
     public JSONObject getTestCaseData(String testCaseId){
         //if array in which entire test data is there is null
         if(testDataArray==null){
             Assert.fail("Test data array is not initialized");
             return null;
         }

         for(int i=0;i<testDataArray.length();i++){
            JSONObject testCase =   testDataArray.getJSONObject(i);
            if(testCase.getString("testCaseId").equalsIgnoreCase(testCaseId)){
                return testCase;
             }
         }

         Assert.fail("Testcase data not found " + testCaseId);
         return null;
     }

    /**
     * Extracts the request body (as JSONObject) for a given test case ID.
     */
    public JSONObject getRequestbody(String testCaseId){
        return getTestCaseData(testCaseId).getJSONObject("requestBody");
    }

    /**
     * Extracts the expected response (as JSONObject) for a given test case ID.
     */
    public JSONObject getResponse(String testCaseId){
        return getTestCaseData(testCaseId).getJSONObject("expectedResponse");
    }



}
