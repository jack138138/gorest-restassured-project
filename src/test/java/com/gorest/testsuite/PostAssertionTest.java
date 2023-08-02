package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends TestBase {
    static ValidatableResponse response;


    public PostAssertionTest() {

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }




    //Verify the if the total record is 25
    @Test
    public void test001(){
        response.body("size", equalTo(25));
    }
    //Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002(){
        response.body("find{it.id == 4136846}.title", equalTo("Vita universe depromo curso thema calcar."));
    }
    //Check the single user_id in the Array list (5522)
    @Test
    public void test003(){
        response.body("user_id", hasItem(4136846) );

    }
    //Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004(){
        response.body("id", hasItems("57612", "57615", "57608"));
    }
    //Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

@Test
    public void test005(){
        response.body("find{it.user_id == 4136831}.body", equalTo("Thema vesper color. Speciosus bos cervus. Comes viscus aiunt. Colo traho quidem. Baiulus verumtamen peior. Testimonium patrocinor dicta. Derideo claudeo eligendi. Et derideo spiculum. Cognatus taedium adulatio. Cupiditate deinde synagoga. Adopto corrupti abutor. Esse teneo curso. Arma voluntarius vobis."));
}
}