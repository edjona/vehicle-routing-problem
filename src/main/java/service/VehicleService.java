package service;

import config.Logger;
import config.MyDatabase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;
import model.Vehicle;
import repository.VehicleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"squid:S106", "squid:S6212"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleService {
    public static List<Vehicle> getRandomVehicles() {
        try{
            var connection = MyDatabase.connect();

            int vehicleCount = VehicleRepository
                    .getVehicles(connection)
                    .size();
            int vehicleCountToRandom = new Random().nextInt(vehicleCount) + 1;

            List<Vehicle> vehicleRandomList = new ArrayList<>();
            for (var index = 0; index < vehicleCountToRandom; index++) {
                vehicleRandomList.add(VehicleRepository
                        .getVehicles(connection)
                        .get(index));
            }

            return vehicleRandomList;

        } catch (SQLException exception) {
            Logger.error(exception.getMessage());
        }
        return getRandomVehicles();
    }

    public static void randomPairing(List<Vehicle> vehicles, List<Demand> demands) {
        Collections.shuffle(demands);
        for (Demand demand : demands) {
            int randomNumber = new Random().nextInt(vehicles.size());

            vehicles
                .get(randomNumber)
                .getRoutes()
                .add(demand.getCustomerId());
        }
    }
}
