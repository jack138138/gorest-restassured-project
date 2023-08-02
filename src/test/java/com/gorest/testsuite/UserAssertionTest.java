package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    public UserAssertionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    //Verify the if the total record is 20
    @Test
    public void test001(){
        response.body("size", equalTo(20));
    }
    //Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002(){
        response.body("name[1]", equalTo("Divjot Agarwal"));
    }
    //Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003(){
       response.body("name[0]", equalTo("Pres. Tanya Bharadwaj"));
    }
    //Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    //Guha, Karthik Dubashi IV)

    @Test
    public void test004(){
        response.body("name", hasItems("The Hon. Somnath Acharya","Mrs. Aadidev Bhat", "Mani Nayar" ));
    }
//Verify the emai/ of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005(){
        response.body("findAll{it.id == 4136843}", hasItem(hasEntry("email", "gowda_i_tanya@pollich.example")));
    }
    //Verify the status is “Active” of user name is “Shanti Bhat V”
@Test
    public void test006(){
        response.body("findAll{it.status == 'active'}", hasItem(hasEntry("name", "Lakshmidhar Mukhopadhyay")));
    }
//Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007(){
        response.body("findAll{it.gender == 'male'}", hasItem(hasEntry("name", "Mani Nayar")));

    }
}
