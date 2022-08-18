package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

     /*
        Given
           1)
           2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
         I send PUT Request to the Urlhttps://jsonplaceholder.typicode.com/todos/198
       Then
             Status code is 200
             And response body is like   {
                               "userId": 21,
                               "title": "Wash the dishes",
                               "completed": false
                              }
     */

    @Test
    public void put01(){
        //1. Step: Set the Url
        spec.pathParams("first","todos","second",198);
        //2. Step: Set the payload
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataWithAllKey(21,"Wash the dishes",false);
        System.out.println(expectedData);

        //3. Step: Send the Put Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));

        assertEquals(200,response.getStatusCode());

    }
}