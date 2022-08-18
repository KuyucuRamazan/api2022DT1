package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
           1) https://jsonplaceholder.typicode.com/todos/198
           2){
                 "title": "Wash the dishes"
               }
        When
         I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                               "userId": 10,
                               "title": "Wash the dishes",
                               "completed": true,
                               "id": 198
                              }
     */
    @Test
    public void patch01(){

        //1. Step: Set the Url
        spec.pathParams("first","todos","second",198);

        //2. Step: Set the Request body
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataWithMissingKeys(null,"Wash the dishes",null);
        System.out.println("expectedData: "+expectedData);

        //3. Step: Send the Patch Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.then().assertThat().statusCode(200).body("title",equalTo(expectedData.get("title")));

        //When you do assertion for Patch request, it is enough to assert the patch part. You can assert whole body as well.
        //1. Way:
        Map<String,Object> expectedDataWithAllKey =  obj.expectedDataWithAllKey(10,"Wash the dishes",true);
        System.out.println("expectedDataWithAllKey: "+expectedDataWithAllKey);
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap: "+actualDataMap);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataWithAllKey.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataWithAllKey.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataWithAllKey.get("completed"),actualDataMap.get("completed"));

        //2. Way:
        response.then().assertThat().statusCode(200).body("userId",equalTo(expectedDataWithAllKey.get("userId")),
                "title",equalTo(expectedDataWithAllKey.get("title")),
                "completed",equalTo(expectedDataWithAllKey.get("completed")));
    }
}