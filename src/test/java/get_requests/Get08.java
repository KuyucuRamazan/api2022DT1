package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
    The biggest challenge in API Testing is data types
    1) Java uses Objects and Primitives as data type, API uses XML, Json, etc.
    Java and API are using different data types, but they should comminicate with each other.
    Comminication is impossible with different data types
    Serialization: To convert Java Object to Json Data
    De-Serialization: To convert Json Fata to Java Object

    To do De-Serialization and Serialization we can use:
    1) Gson: Google created
    2) Object Mapper: More popular
     */

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first", "todos", "second",2 );

        //2. Step: Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println(expectedData);

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //By using GSON we are able to convert Json Data Which is coming from API to java Object
        Map<String,Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData: "+actualData);

        //4.step: Do assertions
        /*assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));*/
    }

}