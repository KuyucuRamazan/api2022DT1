package get_requests;

import base_urls.HerOkuAppBaseUrl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2461
        When
            I send GET Request to the url
        Then
            Response body should be like that;
        {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first", "booking","second", 2461);

        //2. Step: Set the expected data
        Map<String, String>  bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname","James");
        expectedDataMap.put("lastname","Brown");
        expectedDataMap.put("totalprice",111);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates", bookingdatesMap);
        expectedDataMap.put("additionalneeds", "Breakfast");















    }








}