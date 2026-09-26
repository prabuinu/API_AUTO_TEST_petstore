package com.petstore.steps;

import com.petstore.client.OrderClient;
import com.petstore.models.Order;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderSteps {

    private final OrderClient orderClient =
            new OrderClient();

    private Response response;

    private Order order;

    @Given("an order with a known ID has been placed")
    public void orderWithKnownIdHasBeenPlaced() {

        order = new Order(
                20001,
                10001,
                2,
                "2026-09-26T10:00:00.000Z",
                "placed",
                false
        );

        response = orderClient.createOrder(order);

        assertEquals(
                "Unable to create test order",
                200,
                response.statusCode()
        );
    }

    @When("I send a POST to \\/store\\/order with a valid order payload")
    public void sendPostOrder(DataTable dataTable) {

        Map<String, String> data =
                dataTable.asMaps().get(0);

        order = new Order(
                20001,
                Integer.parseInt(data.get("petId")),
                Integer.parseInt(data.get("quantity")),
                "2026-09-26T10:00:00.000Z",
                data.get("status"),
                false
        );

        response = orderClient.createOrder(order);
    }

    @When("I send a GET to \\/store\\/order\\/{int}")
    public void sendGetOrder(int orderId) {

        response = orderClient.getOrder(orderId);
    }

    @When("I send a DELETE to \\/store\\/order\\/{int}")
    public void sendDeleteOrder(int orderId) {

        response = orderClient.deleteOrder(orderId);
    }

    @Then("the response body contains the order id and status {string}")
    public void responseContainsOrderIdAndStatus(
            String expectedStatus) {

        assertEquals(
                "Order ID does not match",
                order.getId(),
                response.jsonPath().getInt("id")
        );

        assertEquals(
                "Order status does not match",
                expectedStatus,
                response.jsonPath().getString("status")
        );
    }

    @Then("the response body contains the correct petId and status")
    public void responseContainsCorrectPetIdAndStatus() {

        assertEquals(
                "Pet ID does not match",
                order.getPetId(),
                response.jsonPath().getInt("petId")
        );

        assertEquals(
                "Order status does not match",
                order.getStatus(),
                response.jsonPath().getString("status")
        );
    }
}