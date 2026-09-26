package com.petstore.client;

import com.petstore.models.Order;
import io.restassured.response.Response;

public class OrderClient {

    private final ApiClient apiClient;

    public OrderClient() {
        this.apiClient = new ApiClient();
    }

    public Response createOrder(Order order) {

        return apiClient.post(
                "/store/order",
                order
        );
    }

    public Response getOrder(int orderId) {

        return apiClient.get(
                "/store/order/" + orderId
        );
    }

    public Response deleteOrder(int orderId) {

        return apiClient.delete(
                "/store/order/" + orderId
        );
    }
}
