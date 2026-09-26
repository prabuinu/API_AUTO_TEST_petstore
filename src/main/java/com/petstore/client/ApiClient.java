package com.petstore.client;

import com.petstore.config.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String BASE_URL =
            ConfigReader.get("BASE_URL");

    private static final String API_KEY =
            ConfigReader.get("API_KEY");

    public Response get(String endpoint) {

        return given()
                .baseUri(BASE_URL)
                .header("api_key", API_KEY)
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, String payload) {

        return given()
                .baseUri(BASE_URL)
                .header("api_key", API_KEY)
                .contentType("application/json")
                .body(payload)
                .when()
                .post(endpoint);
    }

    public Response put(String endpoint, String payload) {

        return given()
                .baseUri(BASE_URL)
                .header("api_key", API_KEY)
                .contentType("application/json")
                .body(payload)
                .when()
                .put(endpoint);
    }

    public Response delete(String endpoint) {

        return given()
                .baseUri(BASE_URL)
                .header("api_key", API_KEY)
                .when()
                .delete(endpoint);
    }
}