@API
Feature: Practice Post and Delete APIs

  @Post
  Scenario: Post API
    When User hits POST API
    Then User validates the status code 201

  @Get
  Scenario: Delete API
    When User hits Delete API
    Then User validates the status code 200