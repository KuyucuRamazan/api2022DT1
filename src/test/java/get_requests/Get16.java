package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.swing.UIManager.getString;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

     /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "[Tatyana Fitzpatrick]"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","employees");

        //2. Set the expected Data

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.then().assertThat().statusCode(200).body("data.id",hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.employee_age<67}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println("Greatest age is " +ageList.get(ageList.size()-1));
        assertEquals(66, (int)ageList.get(ageList.size()-1));
        String nameOfLowestAge = json.getString("data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name");
        System.out.println(nameOfLowestAge);
        assertEquals("[Tatyana Fitzpatrick]", nameOfLowestAge);

        List<Integer> salaryList = json.getList("data.findAll{it.id}.employee_salary");
        System.out.println(salaryList);

        //1.Way:
        int sum=0;
        for(int w:salaryList){
            sum += w;
        }
        System.out.println(sum);

        //2. Way:
        int sum2 = salaryList.stream().reduce(0, (t,u)->(t+u));
        System.out.println(sum2);

        //3. Way:
        int sum3 = salaryList.stream().reduce(0, Math::addExact);
        System.out.println(sum3);

        assertEquals( 6644770, sum3);

//        iii) "Tiger Nixon" and "Garrett Winters" are among the employees
//        iv) The greatest age is 66
//        v) The name of the lowest age is "Tatyana Fitzpatrick"
//        vi) Total salary of all employees is 6,644,770







    }

}