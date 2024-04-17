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

import static org.assertj.core.api.Assertions.assertThat;

public class Testing003 {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingId;

    @BeforeTest
    public void getToken() {
        System.out.println("-- Get Token --");
        requestSpecification = RestAssured.given();
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        Response response = requestSpecification.post();
        validatableResponse = response.then();
        validatableResponse.body("token", Matchers.notNullValue());
        token = response.then().log().all().extract().path("token");
        Assert.assertNotNull(token);
        //assertThat(token).isNotNull().isNotBlank().isNotEmpty();
        System.out.println("token " + token);
    }

    @BeforeTest
    public void getBookingId() {
        System.out.println("-- Get Booking ID --");
        requestSpecification = RestAssured.given();
        String payload = "{\n" +
                "  \"firstname\" : \"Sally\",\n" +
                "  \"lastname\" : \"Brown\",\n" +
                "  \"totalprice\" : 111,\n" +
                "  \"depositpaid\" : true,\n" +
                "  \"additionalneeds\" : \"Breakfast\",\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2013-02-23\",\n" +
                "    \"checkout\" : \"2014-10-23\"\n" +
                "  }\n" +
                "}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);

        // Making Requests
        Response response = requestSpecification.when().post();

        //Validation Part
        ValidatableResponse validatableResponse = response.then();
        String responseString = response.asString();
        System.out.println("responseString " + responseString);
        validatableResponse.statusCode(200);

        //
        bookingId = response.then().log().all().extract().path("bookingid");
        System.out.println("bookingId " + bookingId);


    }

    @Test
    public void getPutRequests() {
        System.out.println("-- Put Request [Updating] --");
        String payload = "{\n" +
                "    \"firstname\" : \"Alex\",\n" +
                "    \"lastname\" : \"Costs\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        //Patch requests

        requestSpecification.body(payload).log().all();

        // Making Requests
        Response response = requestSpecification.when().put();

        //Validation Part
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo("Alex"));
        validatableResponse.body("lastname", Matchers.equalTo("Costs"));
    }
}

