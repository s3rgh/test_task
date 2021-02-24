Feature: Несколько тестов для GET

  Scenario: Check get response status
    Given   The user endpoint exists
    When    I send a valid GET request
    Then    GET response status code should be 200

  Scenario: Check get response time
    Given   The user endpoint exists
    When    I send a valid GET request
    Then    Response time should be less then 200

  Scenario: Make sure the answer contains the specified user
    Given   The user endpoint exists
    When    I send a valid GET request
    Then    Response contains the specified user "Leanne Graham"

  Scenario: Make sure the answer contains the specified header
    Given   The user endpoint exists
    When    I send a valid GET request
    Then    Response contains the specified header "Date"