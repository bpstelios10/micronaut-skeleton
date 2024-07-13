Feature: Private endpoint

  Scenario: Service status endpoint is OK
    When the client makes the call
    Then a response with code 200 is returned
    And the response body is "OK"
