package repository;

import config.HibernateUtil;
import lombok.NoArgsConstructor;
import model.Customer;

import java.util.List;

@NoArgsConstructor
public class CustomerRepository {
    public List<Customer> getCustomers() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT customer FROM Customer customer", Customer.class).list();
        }
    }
}
