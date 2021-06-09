package repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleRepository {
    public static List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        var vehicleOne = new Vehicle();
        vehicleOne.setPoliceNumber("F111XX");
        vehicleOne.setCapacity(240);
        vehicleOne.setCostPerKilometer(0.3);

        var vehicleTwo = new Vehicle();
        vehicleTwo.setPoliceNumber("F122XX");
        vehicleTwo.setCapacity(360);
        vehicleTwo.setCostPerKilometer(0.7);

        var vehicleThree = new Vehicle();
        vehicleThree.setPoliceNumber("F422XX");
        vehicleThree.setCapacity(220);
        vehicleThree.setCostPerKilometer(0.2);

        vehicles.add(vehicleOne);
        vehicles.add(vehicleTwo);
        vehicles.add(vehicleThree);

        return vehicles;
    }
}
