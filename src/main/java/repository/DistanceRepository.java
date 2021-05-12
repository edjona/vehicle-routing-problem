package repository;

import config.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Distance;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceRepository {
    public List<Distance> getDistances() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT distance FROM Distance distance", Distance.class).list();
        }
    }
}
