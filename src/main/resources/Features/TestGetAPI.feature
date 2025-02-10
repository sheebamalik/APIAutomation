@Get
Feature: Practice Get APIs

  @GetWithoutArray
  Scenario: Get API without arraylist
    When User hits GET API without arraylist
    Then User validates the status code 200
    And User validates the response
      | age | 35 |

  @GetArray
  Scenario: Get API with arraylist
    When User hits GET API with arraylist
    Then User validates the status code 200
    And User validates the list response
    | 1 | 2 |

