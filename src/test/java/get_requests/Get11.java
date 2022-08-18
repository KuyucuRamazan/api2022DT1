package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get11 extends GoRestBaseUrl {
      /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            ""Chandrabhan Kakkar", "Girja Pandey","Gayatri Desai"" are among the users
        And
            The female users are more than male users
     */

    @Test
    public void get01(){
        //1.Step: Set Url
        spec.pathParam("first","users");

        //2. Step: Set the expected data

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10), "data.status", hasItem("active"),
                        "data.name", hasItems("Chandrabhan Kakkar", "Girja Pandey","Gayatri Desai"));


        //--The female users are more than male users
        //I will compare number female and male user in 2 ways
        //i) I will get all genders then I will count the females then I will calculate males and I will check which one is more...
        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");
        System.out.println("genders list: "+genders);

        int numOfFemales = 0;
        for(String w:genders){
            if(w.equals("female")){
                numOfFemales++;
            }

        }
        System.out.println("numOfFemales: "+numOfFemales);
        assertTrue(numOfFemales > genders.size() - numOfFemales);

        //ii) I will get all females by using Groovy, i will get all males by Groovy then compare them.
        List<String> femaleList =  json.getList("data.findAll{it.gender=='female'}.gender");//==> This way is recommended...
        System.out.println("femaleList: "+femaleList);//[female, female, female, female, female, female]

        List<String> maleList =  json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("maleList: "+maleList);//[male, male, male, male]

        assertTrue(femaleList.size()>maleList.size());

    }
}