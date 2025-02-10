package com.api.service;

import com.api.dto.Todo;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;


public class TestAPIService
{
    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    private static final ThreadLocal<Integer> actualStatusCode = new ThreadLocal<>();
    private static final ThreadLocal<Integer> expectedStatusCode = new ThreadLocal<>();

    public static void testGetwithoutArray()
    {
        RestAssured.baseURI = "https://api.agify.io/";
        RestAssured.useRelaxedHTTPSValidation();
        setResponse(RestAssured.given().queryParam("name","meelad").when().get());
    }

    public static void testGetWithArray()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos";
        RestAssured.useRelaxedHTTPSValidation();
        setResponse(RestAssured.when().get());
    }

    public static void testPostAPI()
    {
        String body = "{\"userId\":1000," +
                "\"id\":1000," +
                "\"title\":\"sampletitle\"," +
                "\"body\":\"samplebody\"}";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RestAssured.useRelaxedHTTPSValidation();

        setResponse(RestAssured.given().body(body).when().post());
        System.out.println(getResponse());
    }

    public static void testDeleteAPI()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/1";
        RestAssured.useRelaxedHTTPSValidation();

        setResponse(RestAssured.when().delete());
    }

    public static void validateStatusCode(int statusCode)
    {
        setExpectedStatusCode(statusCode);
        setActualStatusCode(getResponse().getStatusCode());
        if(getActualStatusCode() == getExpectedStatusCode())
        {
            Assertions.assertTrue(true, "Test passed");
        }
        else
        {
            Assertions.fail("Status Code:" + getActualStatusCode());
        }
    }

    public static void validateResponse(DataTable table)
    {
        List<String> tableList = table.transpose().asList(String.class);
        String key = tableList.get(0).trim();
        String actualStr = getResponse().getBody().jsonPath().get(key).toString();
        if(tableList.get(1).equals(actualStr))
        {
            Assertions.assertTrue(true, "Test passed");
        }
        else
        {
            Assertions.fail("Actual String:" + actualStr);
        }
    }

    public static void validateResponseWithList(DataTable table)
    {
        List<String> tableList = table.transpose().asList(String.class);
        int index = Integer.parseInt(tableList.get(0).trim());
        String expValue = tableList.get(1).trim();

        Todo[] responseList = getResponse().as(Todo[].class);
        String actualStr = responseList[index].getId();
        if(actualStr.equals(expValue))
        {
            Assertions.assertTrue(true, "test passed");
        }
        else
        {
            Assertions.fail("test failed:"+"actualStr="+actualStr+"expStr="+expValue);
        }
    }

    public static Response getResponse()
    {
        return response.get();
    }

    public static int getActualStatusCode()
    {
        return actualStatusCode.get();
    }

    public static int getExpectedStatusCode()
    {
        return expectedStatusCode.get();
    }

    public static void setResponse(Response res)
    {
        response.set(res);
    }

    public static void setActualStatusCode(Integer actualCode)
    {
        actualStatusCode.set(actualCode);
    }

    public static void setExpectedStatusCode(Integer expCode)
    {
        expectedStatusCode.set(expCode);
    }



}
