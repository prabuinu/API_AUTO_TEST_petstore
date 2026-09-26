Feature: Petstore API

  Background:
    Given the Petstore API is available

  Scenario: Add a new pet successfully
    When I send a POST to pet with a valid pet payload
      | id   | name     | status    |
      | 10001 | Buddy   | available |
    Then the response status is 200
    And the response body contains the same pet id and name I submitted

  Scenario: Add a new pet successfully
    Given the Petstore API is available
    When I send a POST to pet with a valid pet payload
      | id    | name  | status    |
      | 10001 | Buddy | available |
    Then the response status is 200
    And the response body contains the same pet id and name I submitted

  Scenario: Retrieve a pet by ID
    Given a pet with a known ID exists in the store
    When I send a GET to /pet/10001
    Then the response status is 200
    And the response body contains the correct id, name, and status

  Scenario: Delete a pet
    Given a pet with a known ID exists in the store
    When I send a DELETE to /pet/10001 with a valid api_key header
    Then the response status is 200

  Scenario: Get pet by invalid ID returns 404
    When I send a GET to /pet/999999999
    Then the response status is 404
    And the response body contains a "Pet not found" message

  Scenario: Place a new order
    Given the Petstore API is available
    When I send a POST to /store/order with a valid order payload
      | petId | quantity | status |
      | 10001 | 2        | placed |
    Then the response status is 200
    And the response body contains the order id and status "placed"

  Scenario: Retrieve an order by ID
    Given an order with a known ID has been placed
    When I send a GET to /store/order/20001
    Then the response status is 200
    And the response body contains the correct petId and status

  Scenario: Delete an order
    Given an order with a known ID has been placed
    When I send a DELETE to /store/order/20001
    Then the response status is 200

  Scenario: Get order with invalid ID returns 404
    When I send a GET to /store/order/999999999
    Then the response status is 404