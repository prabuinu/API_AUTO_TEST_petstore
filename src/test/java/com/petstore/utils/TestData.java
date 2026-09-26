package com.petstore.utils;
import com.petstore.models.Order;
import com.petstore.models.Pet;

public class TestData {

    public static Pet defaultPet(int id) {

        return new Pet(
                id,
                "Buddy",
                "available"
        );
    }

    public static Order defaultOrder(
            int orderId,
            int petId) {

        return new Order(
                orderId,
                petId,
                2,
                "2026-09-26T10:00:00.000Z",
                "placed",
                false
        );
    }
}