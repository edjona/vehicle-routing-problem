package process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Demand;
import model.Distance;
import repository.DistanceRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitiatePopulation {
    public static int[] activeCustomer(List<Demand> demandList) {
        var customerIds = new int[demandList.size() + 1];

        customerIds[0] = 0;
        for (var index = 1; index < demandList.size(); index++) {
            customerIds[index] = demandList.get(index - 1).getCustomerId();
        }

        return customerIds;
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

    public static double[] activeDistanceBetweenCustomer(int[] customerIds, Connection connection) {
        var ranges = new double[customerIds.length];
        List<Distance> distances = DistanceRepository.getDistances(connection);

        for (var index = 1; index < customerIds.length; index++) {
            var distance = getSelectedDistance(distances, customerIds[index - 1], customerIds[index]);
            ranges[index] = distance != null ? distance.getKilometers() : 0L;
        }

        return ranges;
    }

    public static double countTotalDistance(double[] rangeBetween) {
        return Arrays.stream(rangeBetween).sum();
    }

    private static Distance getSelectedDistance(List<Distance> distances, int from, int to) {
        return distances.stream().filter(
            distance -> distance.getFrom() == from && distance.getTo() == to).findFirst().orElse(null);
    }

}
