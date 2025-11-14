package api.tests;

import com.google.gson.JsonArray;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonDataReader;

/**
 * REST Assured tests for JsonPlaceholder API (typicode.com).
 * Demonstrates CRUD operations and advanced JSON validation using org.json.
 */
public class JsonPlaceholderAPITests {

    private JsonDataReader dataReader;
    private static final String baseUrl = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = baseUrl;
        dataReader = new JsonDataReader();
    }

    //-- Get requests and json validation
    @Test(priority=1)
    public void testGetListofPostAndValidateJsonArray(){

        System.out.println("\n --Running Get list test--");
         Response response =  RestAssured.given()
                            .when()
                            .get("/posts")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

        // Easy/Medium Validation: Check status, content type, and initial structure
        Assert.assertEquals(response.statusCode(),200, "status code check failed");
        Assert.assertEquals(response.contentType(),"application/json", "content type validation failed");

        //advanced json array validation using jsonArray concept
            String responseBody =     response.getBody().asString();
            JSONArray postsArray =  new JSONArray(responseBody);

            //Validation 1 : Ensure it's a non empty array(JSONArray concept)
            Assert.assertTrue(postsArray.length()>0, "array should not be empty");

            //check the structure of first element/object - JSONObject concept
             JSONObject firstPost =  postsArray.getJSONObject(0);
             Assert.assertTrue(firstPost.has("userId"), "First post must have userId field");
             Assert.assertEquals(firstPost.getInt("id"),1, "first post id should be equal to 1");
             System.out.println("Get list test passed : vaidated array size and first element structure");
        }

    @Test(priority=2)
    public void testGetSinglePostRequest(){
        System.out.println("\n -- running single get test-");
              Response response =    RestAssured.given()
                                      .pathParam("posts",5)  //use path parameter for cleaner code
                      .when()
                      .get("/posts/{postId}")
                      .then()
                      .statusCode(200)
                      .extract().response();

              //simple status and essential field check
             Assert.assertEquals(response.statusCode(),200, "status code check failed");

             JSONObject post = new JSONObject(response.getBody().asString());
             Assert.assertEquals(post.getInt("userId"),1, "userId for post 5 should be 1");
             Assert.assertEquals(post.getInt("id"),5,"id for post 5 should be 5");
             Assert.assertFalse(post.getString("title").isEmpty(), "title should not be empty");
             System.out.println("Get single post test passed: " +post.getString("title"));

    }

     @Test(priority=3)
    public void testCreateNewPostAndValidateJsonObject(){
        System.out.println("running single create post");

        //Get test data - request and expected response
          JSONObject testCaseData =   dataReader.getTestCaseData("POST_001_CreatePost");
          JSONObject requestBody =      testCaseData.getJSONObject("requestBody");
          JSONObject expectedResponse =  testCaseData.getJSONObject("expectedResponse");

          //perform post request
          Response response = RestAssured.given()
                  .header("Content-Type", "application/json")
                  .body(requestBody.toString())
                  .when()
                  .post("/posts")
                  .then()
                  .extract().response();

          //advance validation- convert response to jsonobject and compare fields
          JSONObject   actualResponse = new JSONObject(response.getBody().asString());

          //Check the generated id should be present and greater than 100
         Assert.assertTrue(actualResponse.getInt("id")>100,"generated id should be greater than 100");

          //compare fields
         Assert.assertEquals(actualResponse.getString("title"),expectedResponse.getString("title"), "Title mismatch");
         Assert.assertEquals(actualResponse.getString("body"),expectedResponse.getString("body"), "body mismatch");
         Assert.assertEquals(actualResponse.getInt("userId"),expectedResponse.getInt("userId"), "userId mismatch");

         System.out.println("post create test passed :New post id " + actualResponse.getInt("id"));
    }

    @Test(priority=4)
    public void testUpdateExistingPost(){
        System.out.println( "single the update request");

        //Get testdata - request and expected response
          JSONObject testCaseData =      dataReader.getTestCaseData("PUT_001_UpdatePost");
          JSONObject requestBody  =         testCaseData.getJSONObject("requestBody");
          JSONObject expectedResponse = testCaseData.getJSONObject("expectedResponse");

          //perform PUT request
           Response response = RestAssured.given()
                   .header("Content-Type", "application/json")
                   .pathParam("postId", 1)
                   .body(requestBody.toString())
                   .when()
                   .put("/posts/{postId}")
                   .then()
                   .statusCode(200)
                   .extract().response();

        //advance validation- convert response to jsonobject and compare fields
        JSONObject   actualResponse = new JSONObject(response.getBody().asString());

        Assert.assertEquals(actualResponse.getString("title"),expectedResponse.getString("title"), "Title was not updated correctly");
        Assert.assertEquals(actualResponse.getString("body"),expectedResponse.getString("body"), "body mismatch");
        Assert.assertEquals(actualResponse.getInt("userId"),expectedResponse.getInt("userId"), "userId mismatch");
        Assert.assertEquals(actualResponse.getInt("id"),expectedResponse.getInt("id"), "Title mismatch");

    }

}
