package com.api.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.when;

public class TestAPIService
{
    private static String BASE_URL;

    public static void getAPI()
    {
        BASE_URL = "https://catfact.ninja/fact";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = BASE_URL;
        when().get().then().assertThat().statusCode(200);
        ResponseBody responseBody = when().get().body();
        JsonPath jsonPath = responseBody.jsonPath();
        String fact = jsonPath.get("fact");
        System.out.println(fact);
    }

    public static void postAPI()
    {
        int number = 1;
        BASE_URL = "https://jsonplaceholder.typicode.com/posts";
        String jsonBody = "{\"userId\":"+number+"," +
                "\"id\":1," +
                "\"title\":\"delectusautautem\"," +
                "\"completed\":false}";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = BASE_URL;
        int statusCode = RestAssured.given().header("Content-type","application/json").
                and().body(jsonBody).post().statusCode();
        if(statusCode==201)
        {
            Assertions.assertTrue(true, "Test case failed");
        }
        else
        {
            Assertions.fail("Test case failed");
        }
    }

    public static void deleteAPI()
    {
        BASE_URL = "https://jsonplaceholder.typicode.com/posts/1";
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.when().delete().then().assertThat().statusCode(200);
    }

}
