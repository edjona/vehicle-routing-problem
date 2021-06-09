package service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Chromosome;
import model.Demand;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"squid:S106", "squid:S6212"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChromosomeService {
    public static List<Chromosome> buildChromosomeList(int population, List<Demand> demands) {
        List<Chromosome> chromosomes = new ArrayList<>();

        for (var index = 0; index < population; index++) {
            Chromosome chromosome = new Chromosome();
            chromosome.setId(index);

            List<Vehicle> vehicles = VehicleService.getRandomVehicles();
            VehicleService.randomPairing(vehicles, demands);

            chromosome.setVehicles(vehicles);
            chromosomes.add(chromosome);
        }

        return chromosomes;
    }
}
