package com.petstore.steps;

import com.petstore.utils.ApiClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.Map;

import static org.junit.Assert.*;

public class PetSteps {

    private final ApiClient apiClient = new ApiClient();

    private Response response;

    private int petId;
    private String petName;
    private String petStatus;

    private int orderId;
    private int orderPetId;
    private int orderQuantity;
    private String orderStatus;

    @Given("the Petstore API is available")
    public void petstoreApiIsAvailable() {

        response = apiClient.get("/pet/1");

        System.out.println(
                "Petstore API status: " + response.statusCode()
        );
    }

    @Given("a pet with a known ID exists in the store")
    public void petWithKnownIdExists() {

        petId = 10001;
        petName = "Buddy";
        petStatus = "available";

        String payload = """
                {
                  "id": %d,
                  "name": "%s",
                  "status": "%s"
                }
                """.formatted(
                petId,
                petName,
                petStatus
        );

        response = apiClient.post("/pet", payload);

        assertEquals(
                "Unable to create test pet",
                200,
                response.statusCode()
        );

        System.out.println(
                "Test pet created: " + petId
        );
    }

    @When("I send a POST to pet with a valid pet payload")
    public void sendPostWithValidPetPayload(DataTable dataTable) {

        Map<String, String> data =
                dataTable.asMaps().get(0);

        petId = Integer.parseInt(data.get("id"));
        petName = data.get("name");
        petStatus = data.get("status");

        String petPayload = """
                {
                  "id": %d,
                  "name": "%s",
                  "status": "%s"
                }
                """.formatted(
                petId,
                petName,
                petStatus
        );

        response = apiClient.post(
                "/pet",
                petPayload
        );
    }

    @When("I send a GET to \\/pet\\/{int}")
    public void sendGetPet(int petId) {

        this.petId = petId;

        response = apiClient.get(
                "/pet/" + petId
        );
    }

    @When("I send a PUT to \\/pet with the same id and an updated name")
    public void sendPutWithUpdatedName() {

        String updatedName = "BuddyUpdated";

        String petPayload = """
                {
                  "id": %d,
                  "name": "%s",
                  "status": "%s"
                }
                """.formatted(
                petId,
                updatedName,
                petStatus
        );

        petName = updatedName;

        response = apiClient.put(
                "/pet",
                petPayload
        );
    }

    @When("I send a DELETE to \\/pet\\/{int} with a valid api_key header")
    public void sendDeletePet(int petId) {

        this.petId = petId;

        response = apiClient.delete(
                "/pet/" + petId
        );
    }

    @Then("the response status is {int}")
    public void responseStatusIs(int expectedStatus) {

        assertEquals(
                "Unexpected response status",
                expectedStatus,
                response.statusCode()
        );
    }

    @Then("the response body contains the same pet id and name I submitted")
    public void responseContainsSamePetIdAndName() {

        int actualId =
                response.jsonPath().getInt("id");

        String actualName =
                response.jsonPath().getString("name");

        assertEquals(
                "Pet ID does not match",
                petId,
                actualId
        );

        assertEquals(
                "Pet name does not match",
                petName,
                actualName
        );
    }

    @Then("the response body contains the correct id, name, and status")
    public void responseBodyContainsCorrectPetDetails() {

        int actualId =
                response.jsonPath().getInt("id");

        String actualName =
                response.jsonPath().getString("name");

        String actualStatus =
                response.jsonPath().getString("status");

        assertEquals(
                "Pet ID does not match",
                petId,
                actualId
        );

        assertEquals(
                "Pet name does not match",
                petName,
                actualName
        );

        assertEquals(
                "Pet status does not match",
                petStatus,
                actualStatus
        );
    }

    @Then("the response body reflects the updated name")
    public void responseBodyReflectsUpdatedName() {

        String actualName =
                response.jsonPath().getString("name");

        assertEquals(
                "Updated pet name does not match",
                "BuddyUpdated",
                actualName
        );
    }

    @Then("the response body contains a {string} message")
    public void responseBodyContainsMessage(String expectedMessage) {

        String actualMessage =
                response.jsonPath().getString("message");

        assertEquals(
                "Unexpected error message",
                expectedMessage,
                actualMessage
        );
    }

    @Given("an order with a known ID has been placed")
    public void orderWithKnownIdHasBeenPlaced() {

        orderId = 20001;
        orderPetId = 10001;
        orderQuantity = 2;
        orderStatus = "placed";

        String orderPayload = """
                {
                  "id": %d,
                  "petId": %d,
                  "quantity": %d,
                  "shipDate": "2026-09-26T10:00:00.000Z",
                  "status": "%s",
                  "complete": false
                }
                """.formatted(
                orderId,
                orderPetId,
                orderQuantity,
                orderStatus
        );

        response = apiClient.post(
                "/store/order",
                orderPayload
        );

        assertEquals(
                "Unable to create test order",
                200,
                response.statusCode()
        );

        System.out.println(
                "Test order created: " + orderId
        );
    }

    @When("I send a POST to \\/store\\/order with a valid order payload")
    public void i_send_a_post_to_store_order_with_a_valid_order_payload(io.cucumber.datatable.DataTable dataTable) {

        Map<String, String> data =
                dataTable.asMaps().get(0);

        orderId = 20001;

        orderPetId =
                Integer.parseInt(data.get("petId"));

        orderQuantity =
                Integer.parseInt(data.get("quantity"));

        orderStatus =
                data.get("status");

        String orderPayload = """
                {
                  "id": %d,
                  "petId": %d,
                  "quantity": %d,
                  "shipDate": "2026-09-26T10:00:00.000Z",
                  "status": "%s",
                  "complete": false
                }
                """.formatted(
                orderId,
                orderPetId,
                orderQuantity,
                orderStatus
        );

        response = apiClient.post(
                "/store/order",
                orderPayload
        );
    }

    @When("I send a GET to \\/store\\/order\\/{int}")
    public void i_send_a_get_to_store_order(int orderId) {

        response = apiClient.get(
                "/store/order/" + orderId
        );
    }

    @When("I send a DELETE to \\/store\\/order\\/{int}")
    public void i_send_a_delete_to_store_order(int orderId) {

        this.orderId = orderId;

        response = apiClient.delete(
                "/store/order/" + orderId
        );
    }

    @Then("the response body contains the order id and status {string}")
    public void responseContainsOrderIdAndStatus(
            String expectedStatus) {

        int actualOrderId =
                response.jsonPath().getInt("id");

        String actualStatus =
                response.jsonPath().getString("status");

        assertEquals(
                "Order ID does not match",
                orderId,
                actualOrderId
        );

        assertEquals(
                "Order status does not match",
                expectedStatus,
                actualStatus
        );
    }

    @Then("the response body contains the correct petId and status")
    public void responseContainsCorrectPetIdAndStatus() {

        int actualPetId =
                response.jsonPath().getInt("petId");

        String actualStatus =
                response.jsonPath().getString("status");

        assertEquals(
                "Pet ID does not match",
                orderPetId,
                actualPetId
        );

        assertEquals(
                "Order status does not match",
                orderStatus,
                actualStatus
        );
    }
}