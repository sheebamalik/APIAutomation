package com.api.helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class RequestHelper
{
    private static HttpClient httpClient = HttpClient.newHttpClient();
    public static HttpResponse<String> doGetRequest(String url)
    {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse<String>  doGetRequest(String url, Map<String, String> params)
    {
        try
        {

            StringBuilder urlBuilder = new StringBuilder(url + "?");
            for(Map.Entry<String, String> entry :params.entrySet())
            {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlBuilder.setLength(urlBuilder.length()-1);
            url = urlBuilder.toString();


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public static HttpResponse<String> doPostRequest(String url, String body)
    {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static HttpResponse<String> doDeleteRequest(String url)
    {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
