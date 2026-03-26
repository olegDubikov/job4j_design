package ru.job4j.ood.lsp.parking.parkingPlace;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public class ParkingPlaceImpl implements ParkingPlace {
    private Vehicle vehicle;

    @Override
    public boolean canPark(Vehicle vehicle) {
        return this.vehicle == null;
    }

    @Override
    public boolean isParked() {
        return vehicle != null;
    }

    @Override
    public void park(Vehicle vehicle) {
        if (canPark(vehicle)) {
            this.vehicle = vehicle;
        } else {
            throw new IllegalStateException("Место занято");
        }
    }
}