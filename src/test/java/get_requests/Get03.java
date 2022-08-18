package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

      /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be “application/json”
      And
          “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
      And
          “completed” is false
      And
          “userId” is 2
     */

    @Test
    public void get01(){
        //1. Step: Set the url
        //String url = "https://jsonplaceholder.typicode.com/todos/23";//This is not recommended.
        spec.pathParams("first","todos","second",23);

        //2. Step: Set the expected data

        //3. Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion:
        //1. Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        //2. Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),"userId",equalTo(2));


        /*
        Note 1: When you execute assertions, Java stops execution just after the first failure, it means assertions after the failure were not executed.
                If assertions were not executed  you can not tell anything about their states. They may pass or they may fail.
                But the assertions before failure were passed, because assertions before failure executed.
        Note 2: If you type your code as execution will stop in the first failure, this is called "Hard Assertion"
        Note 3: If you type your code as execution will not stop in  any failure, this is called "Soft Assertion"
        Note 4: If you use multiple body() method, it will work like "Hard Assertion".
                If you use just a single boody() method with multiple assertions in it, it will work like "Soft Assertion".






         */







    }

}