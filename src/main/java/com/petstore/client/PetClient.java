package com.petstore.client;

import com.petstore.models.Pet;
import io.restassured.response.Response;

public class PetClient {

    private final ApiClient apiClient;

    public PetClient() {
        this.apiClient = new ApiClient();
    }

    public Response createPet(Pet pet) {

        return apiClient.post(
                "/pet",
                pet
        );
    }

    public Response getPet(int petId) {

        return apiClient.get(
                "/pet/" + petId
        );
    }

    public Response updatePet(Pet pet) {

        return apiClient.put(
                "/pet",
                pet
        );
    }

    public Response deletePet(int petId) {

        return apiClient.delete(
                "/pet/" + petId
        );
    }
}
