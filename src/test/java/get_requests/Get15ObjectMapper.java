package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {

    /*
            Given
                   https://restful-booker.herokuapp.com/booking/23
            When
                I send GET Request to the URL
          Then
                Status code is 200
                   {
                        "firstname": "Jim",
                        "lastname": "Brown",
                        "totalprice": 1596320,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "Breakfast"
                    }
         */
    @Test
    public void get01ObjectMapper(){
        //1. Step: Set the Url
        spec.pathParams("first","booking", "second",23);

        //2. Step: Set the expected data
        String expectedData = "{\n" +
                "                    \"firstname\": \"Jim\",\n" +
                "                    \"lastname\": \"Brown\",\n" +
                "                    \"totalprice\": 1596320,\n" +
                "                    \"depositpaid\": true,\n" +
                "                    \"bookingdates\": {\n" +
                "                        \"checkin\": \"2018-01-01\",\n" +
                "                        \"checkout\": \"2019-01-01\"\n" +
                "                    },\n" +
                "                    \"additionalneeds\": \"Breakfast\"\n" +
                "                }";

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);
        System.out.println(expectedDataPojo);

        //3rd Step: Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4th Step: Do assertion

        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        System.out.println(actualDataPojo);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(), actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(), actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(), actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

        //Soft Assertion
        //1) Create SoftAssert object

        SoftAssert softAssert = new SoftAssert();

        //2) Do assertion
        softAssert.assertEquals(response.getStatusCode(),200,  "The Status Code does not match" );
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(), "Firstname does not match");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),  "Lastname does not match ");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),  "Totalprice does not match");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(), expectedDataPojo.getDepositpaid(), "Deposit paid does not match");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),  "Additional needs does not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Checkin does not match" );
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(), "Checkout does not match");
        //3) Use assertAll() Method
softAssert.assertAll();
    }


}