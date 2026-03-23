package ru.job4j.ood.lsp.parking.parkingPlace;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public interface ParkingPlace {
    boolean canPark(Vehicle vehicle);
    boolean isParked();
    void park(Vehicle vehicle);
}