package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
     1) Postman is used for manual API testing.
     2) We use Rest-Assured Library for API Automation Testing.
     3) To type automation script follow the given steps:
        a) Understand the requirement
        b) Type test case
                To type test cases we use 'Gherkin Language'
                'Gherkin Language' has some keywords to type test cases.
                The keywords are: i) Given: It is for the pre-requisites.
                                  ii)When: It is for action
                                  iii)Then: It is used for outputs.
                                  iv) And: It is used for multiple given, when and then.
        c) Start to type the Automation Script.
            1) Set the URL
            2) Set the expected data
            3) Type code to send request.
            4) Do Assertion

     */

     /*
        Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){
        //1) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/101";
        //2) Set the expected data

        //3) Type code to send request and get the Response.
        Response response = given().when().get(url);
        response.prettyPrint();

        //4) Do Assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        System.out.println("Status Code is " +response.getStatusCode());
        System.out.println("Status Line is " +response.getStatusLine());
        System.out.println("Status Type is " +response.getContentType());
        System.out.println("Time is " +response.getTime());
        System.out.println("Headers are \n " +response.getHeaders());
        System.out.println("Via is " +response.getHeader("Via"));

    }












}