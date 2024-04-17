package org.example;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Request_Body {

   @Test
    public void testAllNegativeTestCase(){
       RequestSpecification r = RestAssured.given();
       r.baseUri("https://restful-booker.herokupp.com");
       r.basePath("booking/abc").log().all();
       r.when().get();
       r.then().log().all().statusCode(404);
    }
}
