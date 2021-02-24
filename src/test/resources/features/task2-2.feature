Feature: Несколько тестов для POST

  Scenario: Verify the response code
    Given   The users endpoint exists
    When    I send a valid POST request
    Then    POST response status code should be 201

  Scenario: Verify the body of the response
    Given   The users endpoint exists
    When    I send a valid POST request
    Then    POST response body should exist

  Scenario: Required fields only in response body
    Given   The users endpoint exists
    When    I send a valid POST request
    Then    Response contains new user "Sergey"