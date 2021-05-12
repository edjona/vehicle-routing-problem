package process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;
import repository.DemandRepository;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitiatePopulation {
    public static List<Demand> getDemandsByDate(String dateString) {
        return DemandRepository.getDemandsByDate(Date.valueOf(dateString));
    }
}
