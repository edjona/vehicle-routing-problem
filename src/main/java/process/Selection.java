package process;


import model.Distance;
import model.Vehicle;
import repository.DistanceRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selection {
    private static java.sql.Connection Connection;

    public static ArrayList<Object> totalSelectedDistance(int[] activeCustomerIds, Connection connection) {
        List<Distance> distances = DistanceRepository.getDistances(Connection);
        var sumSelectedDistance = new ArrayList<>();

        for (var index = 1; index < activeCustomerIds.length; index++) {
            var distance = getSelectedDistance(distances, activeCustomerIds[index - 1], activeCustomerIds[index]);
            sumSelectedDistance.add(distance);
        }
        return sumSelectedDistance;
    }

    public static double sumTotalSelectedDistance (double[] selectedDistance){
        return Arrays.stream(selectedDistance).sum();
    }

    private static Distance getSelectedDistance(List<Distance> distances, int from, int to) {
        return distances.stream().filter(
                distance -> distance.getFrom() == from && distance.getTo() == to).findFirst().orElse(null);
    }

    private static Vehicle getSelectedVehicle(List<Vehicle> vehicles, int id, int capacity, int availability) {
        return vehicles.stream().filter(
                vehicle -> vehicle.getId() == id && vehicle.getCapacity() == capacity && vehicle.getAvailability() == availability).findFirst().orElse(null);
    }
}
