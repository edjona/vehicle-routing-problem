package repository;

import config.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemandRepository {
    public static List<Demand> getDemandsByDate(Connection connection, String dateString) {
        try (var statement = connection.createStatement()) {
            List<Demand> demands = new ArrayList<>();
            var resultSet = statement.executeQuery("SELECT * FROM demand WHERE date = '" + dateString + "'");

            fetchData(resultSet, demands);

            return demands;
        } catch (SQLException exception) {
            Logger.systemInfo("Failed to fetch demand data", "MySQL Database");
            Logger.error(exception.getMessage());

            return new ArrayList<>();
        }
    }

    private static void fetchData(ResultSet resultSet, List<Demand> demands) throws SQLException {
        while (resultSet.next()) {
            var demand = new Demand();

            demand.setId(resultSet.getInt("id"));
            demand.setDate(resultSet.getDate("date"));
            demand.setQuantity(resultSet.getInt("quantity"));
            demand.setRoute(resultSet.getString("route"));
            demand.setVehicleId(resultSet.getInt("id_vehicle"));
            demand.setCustomerId(resultSet.getInt("id_customer"));

            demands.add(demand);
        }
    }
}