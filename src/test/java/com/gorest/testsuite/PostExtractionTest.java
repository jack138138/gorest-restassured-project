package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest extends TestBase {
    static ValidatableResponse response;

    public PostExtractionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/post")
                .then().statusCode(200);

    }

    //Extract the title
    @Test
    public void test001() {
        String title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All titles are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the total number of record
    @Test
    public void test002() {
        int total = response.extract().path("totel.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the total number of record is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of 15th record
    @Test
    public void test003() {
        List<String> body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> user = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the user_id of all the records are : " + user);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the title of all the records
    @Test
    public void test005() {
        List<HashMap<String, ?>> all = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the title of all the records : " + all);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all records whose user_id = 4145643
    @Test
    public void test006() {
        List<?> title = response.extract().path("findAll{it.user_id == 4145643}.title");
        System.out.println("--------------------Start of Test--------------------");
        System.out.println("Title of all the records whose id 4145643 is : " + title);
        System.out.println("---------------------End of Test---------------------");
    }

    //Extract the body of all records whose id = 57667
    public void test007() {
        List<?> body = response.extract().path("findAll{it.id == 57667}.body");
        System.out.println("--------------------Start of Test--------------------");
        System.out.println("Body of all the records whose id is 57667 is: " + body);
        System.out.println("---------------------End of Test---------------------");
    }

}