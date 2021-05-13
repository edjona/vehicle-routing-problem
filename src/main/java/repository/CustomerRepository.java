package repository;

import config.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRepository {
    public static List<Customer> getCustomers(Connection connection) {
        try (var statement = connection.createStatement()) {
            List<Customer> customers = new ArrayList<>();
            var resultSet = statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {
                var customer = new model.Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customers.add(customer);
            }

            return customers;
        } catch (SQLException exception) {
            Logger.systemInfo("Failed to fetch customer data", "MySQL Database");
            Logger.error(exception.getMessage());

            return new ArrayList<>();
        }
    }
}
