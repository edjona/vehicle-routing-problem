package repository;

import config.HibernateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemandRepository {
    public static List<Demand> getDemands() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT demand FROM Demand demand", Demand.class).list();
        }
    }
}