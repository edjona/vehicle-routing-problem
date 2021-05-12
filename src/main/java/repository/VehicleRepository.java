package repository;

import config.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Vehicle;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleRepository {
    public List<Vehicle> getVehicles() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT vehicle FROM Vehicle vehicle", Vehicle.class).list();
        }
    }
}