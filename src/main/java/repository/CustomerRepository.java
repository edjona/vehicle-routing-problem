package repository;

import config.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Customer;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRepository {
    public static List<Customer> getCustomers() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT customer FROM Customer customer", Customer.class).list();
        }
    }
}
