package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;



public class Get02 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get01(){

        //1. Step: Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/1";
        //2. Step: Set the excepted data

        //3. Step: Send get request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        //4. Step: Do Assertion
        response.
                then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        //How to assert if response body has a specific text
        //assertTrue(x) method passes when the x is true
        assertTrue(response.asString().contains("Not Found"));

        //How to assert if response body does not have a specific text
        //assertFalse(x) method passes when the x is false
        assertFalse(response.asString().contains("TechProEd"));

        //assertEquals(a, b) method passes if a equals b
        assertEquals("Cowboy",response.getHeader("Server"));

    }
}


