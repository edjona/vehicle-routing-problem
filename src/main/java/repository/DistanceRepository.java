package repository;

import config.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Distance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceRepository {
    public static List<Distance> getDistances(Connection connection) {
        try (var statement = connection.createStatement()) {
            List<Distance> distances = new ArrayList<>();
            var resultSet = statement.executeQuery("SELECT * FROM distance");

            fetchData(distances, resultSet);

            return distances;
        } catch (SQLException exception) {
            Logger.systemInfo("Failed to fetch distance data", "MySQL Database");
            Logger.error(exception.getMessage());

            return new ArrayList<>();
        }
    }

    private static void fetchData(List<Distance> distances, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            var distance = new Distance();

            distance.setId(resultSet.getInt("id"));
            distance.setFrom(resultSet.getInt("from"));
            distance.setTo(resultSet.getInt("to"));
            distance.setKilometers(resultSet.getDouble("kilometers"));

            distances.add(distance);
        }
    }
}
