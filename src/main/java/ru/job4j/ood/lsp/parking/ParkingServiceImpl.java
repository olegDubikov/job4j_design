package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.parkingPlace.ParkingPlace;
import ru.job4j.ood.lsp.parking.parkingPlace.ParkingPlaceImpl;
import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class ParkingServiceImpl implements ParkingService {
    private final List<ParkingPlace> carPlaces;
    private final List<ParkingPlace> truckPlaces;

    public ParkingServiceImpl(int carCount, int truckCount) {
        this.carPlaces = new ArrayList<>();
        for (int i = 0; i < carCount; i++) {
            carPlaces.add(new ParkingPlaceImpl());
        }
        this.truckPlaces = new ArrayList<>();
        for (int i = 0; i < truckCount; i++) {
            truckPlaces.add(new ParkingPlaceImpl());
        }
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle) {
        boolean rls = false;
        if (vehicle.size() == 1) {
            for (ParkingPlace place : carPlaces) {
                if (!place.isParked()) {
                    place.park(vehicle);
                    rls = true;
                    break;
                }
            }
        } else {
            for (ParkingPlace place : truckPlaces) {
                if (!place.isParked()) {
                    place.park(vehicle);
                    rls = true;
                    break;
                }
            }
            int count = 0;
            for (int i = 0; i < carPlaces.size(); i++) {
                if (!carPlaces.get(i).isParked()) {
                    count++;
                    if (count == vehicle.size()) {
                        for (int j = i - vehicle.size() + 1; j <= i; j++) {
                            carPlaces.get(j).park(vehicle);
                        }
                        rls = true;
                        break;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return rls;
    }
}