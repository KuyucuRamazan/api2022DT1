package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends HerOkuAppBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/22
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is “application/json”
       And
           Response body should be like;
         {
           "firstname": "Sally",
           "lastname": "Brown",
           "totalprice": 111,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2013-02-23",
               "checkout": "2014-10-23"
           },
           "additionalneeds": "Breakfast"
       }
    */
    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","booking","second", 22);
        //2. Step: Set the exppected data

        //3. Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion

        //1. Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname", equalTo("Brown"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2013-02-23"),
                        "bookingdates.checkout",equalTo("2014-10-23"),
                        "additionalneeds",equalTo("Breakfast"));

        //2. Way: We will use JsonPath Class
        JsonPath json = response.jsonPath();

        assertEquals("Sally", json.getString("firstname"));
        assertEquals("Brown", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertEquals(true, json.getBoolean("depositpaid"));
        assertEquals("2013-02-23", json.getString("bookingdates.checkin"));
        assertEquals("2014-10-23", json.getString("bookingdates.checkout"));
        assertEquals("Breakfast", json.getString("additionalneeds"));

        //3. Way: Use soft assertion(Verification)
        // To use Soft Assertion follow 3 steps
        // I. Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        // II. Do Assertion
        softAssert.assertEquals(json.getString("firstname"),"Sally","Firstname did not match");
        softAssert.assertEquals(json.getString("lastname"),"Brown","Lastname did not match");
        softAssert.assertEquals(json.getInt("totalprice"),111,"Total price did not match");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"Deposit paid did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2013-02-23","Checkin did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2014-10-23","Checkout did not match");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast","Checkout did not match");

        // III. Use assertAll() Method. If you do not use  assertAll() method, you will get pass message every time even if they not pass.
        softAssert.assertAll();

    }
}