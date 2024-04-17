package org.example.Testng;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.Matcher.*;

public class Testing002 {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    String token;
    @Test
    public void getToken(){
        requestSpecification = RestAssured.given();
        String payload = "{\n" + "\"username\": \"admin\",\n" + "\"password\": \"password123\"\n"+"}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        Response response = requestSpecification.post();
        validatableResponse = response.then();
        validatableResponse.body("token" , Matchers.notNullValue());
        token = response.then().log().all().extract().path("token");
        Assert.assertNotNull(token);
        assertThat(token).isNotNull().isNotBlank().isNotEmpty();
        System.out.println("token " + token);

    }
    public void TastCase1_put_Request(){

    }
}
