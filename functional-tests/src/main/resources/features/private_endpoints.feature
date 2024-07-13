Feature: Private endpoint

  Scenario: Service status endpoint is OK
    When the client makes the call to private-status endpoint
    Then a response with code 200 is returned
    And the response body is "OK"

  Scenario: Service non existing endpoint is NOT_FOUND
    When the client makes the call to non-existing endpoint
    Then a response with code 404 is returned
