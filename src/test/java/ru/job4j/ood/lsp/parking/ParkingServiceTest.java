package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class ParkingServiceTest {
/*
    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        // 5 мест для легковых, 2 для грузовых
        parkingService = new ParkingService(5, 2);
    }

    @Test
    void whenParkCarFreePlace() {
        assertTrue(parkingService.parkVehicle(new Car()));
    }

    @Test
    void whenParkTwoCarsThenTrue() {
        assertTrue(parkingService.parkVehicle(new Car()));
        assertTrue(parkingService.parkVehicle(new Car()));
    }

    @Test
    void whenParkingForCarsIsFull() {
        for (int i = 0; i < 5; i++) {
            assertTrue(parkingService.parkVehicle(new Car()));
        }
        assertFalse(parkingService.parkVehicle(new Car()));
    }

    @Test
    void whenParkTruckFreePlace() {
        assertTrue(parkingService.parkVehicle(new Truck(2)));
    }

    @Test
    void whenParkTruckOnCarPlace() {
        parkingService.parkVehicle(new Car());
        parkingService.parkVehicle(new Car());
        parkingService.parkVehicle(new Car());
        assertTrue(parkingService.parkVehicle(new Truck(2)));
    }

    @Test
    void whenNotEnoughPlaceForTruck() {
        parkingService.parkVehicle(new Car());
        parkingService.parkVehicle(new Truck(2));
        parkingService.parkVehicle(new Car());
        assertFalse(parkingService.parkVehicle(new Truck(2)));
    }

    @Test
    void whenParkTruckOnTruckPlace() {
        assertTrue(parkingService.parkVehicle(new Truck(3)));
    }

    @Test
    void whenNoPlacesForTruck() {
        parkingService.parkVehicle(new Truck(3));
        parkingService.parkVehicle(new Truck(3));
        assertFalse(parkingService.parkVehicle(new Truck(3)));
    }
    */
}