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

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class VehicleRepository {
    public static List<Vehicle> getVehicles (Connection connection) {
        try (var statement = connection.createStatement()) {
            List<Vehicle> vehicles = new ArrayList<>();
            var resultSet = statement.executeQuery("SELECT * FROM vehicle");

            fetchData(vehicles, resultSet);

            return vehicles;
        } catch (SQLException exception) {
            Logger.systemInfo("Failed to fetch distance data", "MySQL Database");
            Logger.error(exception.getMessage());

            return new ArrayList<>();
        }
    }

        private static void fetchData(List<Vehicle> vehicles, ResultSet resultSet) throws SQLException {
            while (resultSet.next()) {
                var vehicle = new Vehicle();

                vehicle.setId(resultSet.getInt("id"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setVehicle_police_id(resultSet.getString("vehicle_police_id"));
                vehicle.setCapacity(resultSet.getInt("capacity"));
                vehicle.setKilos_fuel(resultSet.getDouble("kilos_fuel"));

                vehicles.add(vehicle);
            }
        }

}
