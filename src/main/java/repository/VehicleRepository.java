package repository;

import config.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Vehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleRepository {
    public static List<Vehicle> getVehicles(Connection connection) {
        try (var statement = connection.createStatement()) {
            List<Vehicle> vehicles = new ArrayList<>();
            var resultSet = statement.executeQuery("SELECT * FROM vehicle");

            fetchData(vehicles, resultSet);

            return vehicles;
        } catch (SQLException exception){
            Logger.systemInfo("Failed to fetch distance data", "MySQL Database");
            Logger.error(exception.getMessage());

            return new ArrayList<>();
        }
//        List<Vehicle> vehicles = new ArrayList<>();
//
//        var vehicleOne = new Vehicle();
//        vehicleOne.setPolice_number("F111XX");
//        vehicleOne.setCapacity(240);
//        vehicleOne.setKilos_fuel(0.3);
//
//        var vehicleTwo = new Vehicle();
//        vehicleTwo.setPolice_number("F122XX");
//        vehicleTwo.setCapacity(360);
//        vehicleTwo.setKilos_fuel(0.7);
//
//        var vehicleThree = new Vehicle();
//        vehicleThree.setPolice_number("F422XX");
//        vehicleThree.setCapacity(220);
//        vehicleThree.setKilos_fuel(0.2);
//
//        vehicles.add(vehicleOne);
//        vehicles.add(vehicleTwo);
//        vehicles.add(vehicleThree);
//
//        return vehicles;
    }
    private static void fetchData(List<Vehicle> vehicles, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            var vehicle = new Vehicle();

            vehicle.setId(resultSet.getInt("id"));
            vehicle.setType(resultSet.getString("type"));
            vehicle.setPolice_number(resultSet.getString("police_number"));
            vehicle.setCapacity(resultSet.getInt("capacity"));
            vehicle.setKilos_fuel(resultSet.getDouble("kilos_fuel"));

            vehicles.add(vehicle);
        }
    }
}
