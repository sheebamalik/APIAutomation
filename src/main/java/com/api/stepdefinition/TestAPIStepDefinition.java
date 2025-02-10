package com.api.stepdefinition;

import com.api.service.TestAPIService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestAPIStepDefinition
{
    @When("User hits GET API without arraylist")
    public void userHitsGETAPIWithoutArraylist()
    {
        TestAPIService.testGetwithoutArray();
    }

    @When("User hits GET API with arraylist")
    public void userHitsGETAPIWithArraylist()
    {
        TestAPIService.testGetWithArray();
    }

    @When("User hits POST API")
    public void userHitsPOSTAPI()
    {
        TestAPIService.testPostAPI();
    }

    @When("User hits Delete API")
    public void userHitsDeleteAPI()
    {
        TestAPIService.testDeleteAPI();
    }

    @And("User validates the response")
    public void userValidatesTheResponse(DataTable table)
    {
        TestAPIService.validateResponse(table);
    }

    @And("User validates the list response")
    public void userValidatesTheListResponse(DataTable table)
    {
        TestAPIService.validateResponseWithList(table);
    }

    @Then("User validates the status code {int}")
    public void userValidatesTheStatusCode(int status)
    {
        TestAPIService.validateStatusCode(status);
    }


}
