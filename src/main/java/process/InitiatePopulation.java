package process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;
import model.Distance;
import repository.DemandRepository;
import repository.DistanceRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitiatePopulation {
    public static List<Demand> getDemandsByDate(String dateString) {
        return DemandRepository.getDemandsByDate(Date.valueOf(dateString));
    }

    public static List<List<Demand>> createDemandPopulations(List<Demand> demandList, int population) {
        List<List<Demand>> demandPopulations = new ArrayList<>();

        for (var index = 0; index < population; index++) {
            List<Demand> demandTemp = new ArrayList<>(demandList);
            Collections.shuffle(demandTemp);
            demandPopulations.add(demandTemp);
        }

        return demandPopulations;
    }

    public static double countTotalDistance(List<Demand> demands) {
        List<Distance> distances = DistanceRepository.getDistances();
        double tempDistance = 0;

        for (var index = 0; index < demands.size(); index++) {
            Distance tempDist;

            if (index == 0) {
                tempDist = getSelectedDistance(distances, 0, demands.get(index).getCustomerId());
            } else {
                if (index == demands.size() - 1) {
                    tempDist = getSelectedDistance(distances, demands.get(index).getCustomerId(), 0);
                } else {
                    int idFrom = demands.get(index).getCustomerId();
                    int idTo = demands.get(index + 1).getCustomerId();
                    tempDist = getSelectedDistance(distances, idFrom, idTo);
                }
            }

            if (tempDist != null) {
                tempDistance += tempDist.getKilometers();
            }
        }

        return tempDistance;
    }

    private static Distance getSelectedDistance(List<Distance> distances, int from, int to) {
        return distances.stream().filter(
            distance -> distance.getFrom() == from && distance.getTo() == to).findFirst().orElse(null);
    }

}
