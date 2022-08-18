package get_requests;

import base_urls.DummyRestApiBaseUrl;
import org.junit.Test;

public class Get17 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends Get Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
    */
@Test
    public void get01(){
    //1.Step: Set theUrl
    spec.pathParams("first", "employee", "second", 1);

    //2. Step: Set the expected
}
}
