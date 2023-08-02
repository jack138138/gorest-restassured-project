package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    public UserExtractionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    // Extract the All Ids
    @Test
    public void test001()
    {
        List<Integer>  allId=response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Id's are : "+allId);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the all Names
    @Test
    public void test002()
    {
        List<String>  allName=response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Id's are : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003()
    {
        // Extract the name of 5th object
        String singleName =response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name is : "+singleName);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the names of all object whose status = inactive
    @Test
    public void test005()
    {
        List<String> allName=response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object name whose status is inactive : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the gender of all the object whose status = active
    @Test
    public void test006()
    {
        List<String> allName=response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All genders whose status is active : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the names of the object whose gender = female
    @Test
    public void test007()
    {
        List<String> allName=response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All name of gender female : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test008()
    {
        List<String> allEmail=response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All email whose status is inactive : "+allEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test009()
    {
        List<String> allId=response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All id whose gender is male : "+allId);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test010()
    {
        List<String> allStatus=response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All status : "+allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test011(){
        List<HashMap<String, ?>> singleEmail =response.extract().path("findAll{it.name=='Sukanya Mahajan'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email is : "+singleEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471

    @Test
    public void test012(){
        List<String> getGender =response.extract().path("findAll{it.id==4136846}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is : "+getGender);
        System.out.println("------------------End of Test---------------------------");
    }
}
