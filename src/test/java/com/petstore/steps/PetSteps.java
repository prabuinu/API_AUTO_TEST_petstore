package com.petstore.steps;

import com.petstore.client.PetClient;
import com.petstore.models.Pet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PetSteps {

    private final PetClient petClient = new PetClient();

    private Response response;

    private Pet pet;

    @Given("the Petstore API is available")
    public void petstoreApiIsAvailable() {

        response = petClient.getPet(1);

        System.out.println(
                "Petstore API status: "
                        + response.statusCode()
        );
    }

    @Given("a pet with a known ID exists in the store")
    public void petWithKnownIdExists() {

        pet = new Pet(
                10001,
                "Buddy",
                "available"
        );

        response = petClient.createPet(pet);

        assertEquals(
                "Unable to create test pet",
                200,
                response.statusCode()
        );
    }

    @When("I send a POST to pet with a valid pet payload")
    public void sendPostWithValidPetPayload(
            DataTable dataTable) {

        Map<String, String> data =
                dataTable.asMaps().get(0);

        pet = new Pet(
                Integer.parseInt(data.get("id")),
                data.get("name"),
                data.get("status")
        );

        response = petClient.createPet(pet);
    }

    @When("I send a GET to \\/pet\\/{int}")
    public void sendGetPet(int petId) {

        response = petClient.getPet(petId);
    }

    @When("I send a PUT to \\/pet with the same id and an updated name")
    public void sendPutWithUpdatedName() {

        pet.setName("BuddyUpdated");

        response = petClient.updatePet(pet);
    }

    @When("I send a DELETE to \\/pet\\/{int} with a valid api_key header")
    public void sendDeletePet(int petId) {

        response = petClient.deletePet(petId);
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
                pet.getId(),
                actualId
        );

        assertEquals(
                "Pet name does not match",
                pet.getName(),
                actualName
        );
    }

    @Then("the response body contains the correct id, name, and status")
    public void responseBodyContainsCorrectPetDetails() {

        assertEquals(
                "Pet ID does not match",
                pet.getId(),
                response.jsonPath().getInt("id")
        );

        assertEquals(
                "Pet name does not match",
                pet.getName(),
                response.jsonPath().getString("name")
        );

        assertEquals(
                "Pet status does not match",
                pet.getStatus(),
                response.jsonPath().getString("status")
        );
    }

    @Then("the response body reflects the updated name")
    public void responseBodyReflectsUpdatedName() {

        assertEquals(
                "Updated pet name does not match",
                "BuddyUpdated",
                response.jsonPath().getString("name")
        );
    }

    @Then("the response body contains a {string} message")
    public void responseBodyContainsMessage(
            String expectedMessage) {

        assertEquals(
                "Unexpected error message",
                expectedMessage,
                response.jsonPath().getString("message")
        );
    }
}