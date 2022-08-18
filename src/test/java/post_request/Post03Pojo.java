package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

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
        spec.pathParam("first","todos");

        //2. Step: Set the expected data
        JsonPlaceHolderPojo payload = new JsonPlaceHolderPojo(55,"Tidy your room please",false);
        System.out.println("ExpectedData: "+payload);

        //3. Step: Send Post Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(payload).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData: "+actualData);
        assertEquals(payload.getUserId(),actualData.getUserId());
        assertEquals(payload.getTitle(),actualData.getTitle());
        assertEquals(payload.getCompleted(),actualData.getCompleted());
    }
}