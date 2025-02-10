package com.api.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredHelper
{
    private static final ThreadLocal<RestAssured> restAssured = new ThreadLocal<>();


    public static RestAssured getRestAssured()
    {
        return restAssured.get();
    }


}
