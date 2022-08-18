package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {

/*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){
        //1. Step: Set the Url
        spec.pathParam("first", "todos");

        //2. Step: Set the expected data(Request Body - Payload)
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataWithAllKey(55,"Tidy your room",false);
        System.out.println(expectedData);

        //3. Step: Send Post Request and get the Response
        Response response = given().
                spec(spec).auth().
                basic("admin", "1234").
                contentType(ContentType.JSON).
                body(expectedData).
                when().
                post("/{first}");
        response.prettyPrint();
//I added Status Code to use it in assertion
       // expectedDataMap.put("StatusCode", 201);
        //4. Step: Do Assertion

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        //assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

        assertEquals(201,response.getStatusCode());

    }
}