package com.api.service;

import com.api.dto.Todo;
import com.api.helper.RequestHelper;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestAPIService
{
    private static final ThreadLocal<HttpResponse<String>> response = new ThreadLocal<>();
    private static final ThreadLocal<Integer> actualStatusCode = new ThreadLocal<>();
    private static final ThreadLocal<Integer> expectedStatusCode = new ThreadLocal<>();

    public static void testGetwithoutArray()
    {
        HttpResponse<String> result = RequestHelper.doGetRequest("https://api.agify.io/", Map.of("name", "meelad"));
        setResponse(result);
    }

    public static void testGetWithArray()
    {
        HttpResponse<String> result = RequestHelper.doGetRequest("https://jsonplaceholder.typicode.com/todos");
        setResponse(result);
    }

    public static void testPostAPI()
    {
        String body = "{\"userId\":1000," +
                "\"id\":1000," +
                "\"title\":\"sampletitle\"," +
                "\"body\":\"samplebody\"}";

        HttpResponse<String> result = RequestHelper.doPostRequest("https://jsonplaceholder.typicode.com/posts",
                body);
        setResponse(result);
    }

    public static void testDeleteAPI()
    {
        HttpResponse<String> result = RequestHelper.doDeleteRequest("https://jsonplaceholder.typicode.com/posts/1");
        setResponse(result);
    }

    public static void validateStatusCode(int statusCode)
    {
        setExpectedStatusCode(statusCode);
        setActualStatusCode(getResponse().statusCode());
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
        String res = getResponse().body();
        String actualStr = null;
        try
        {
            actualStr = new ObjectMapper().readValue(res, HashMap.class).get(key).toString();
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
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

        Todo[] responseList = null;
        try
        {
            responseList = new ObjectMapper().readValue(getResponse().body(), Todo[].class);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
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

    public static HttpResponse<String> getResponse()
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

    public static void setResponse(HttpResponse<String> res)
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
