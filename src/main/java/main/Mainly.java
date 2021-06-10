package main;

import config.Logger;
import config.MyDatabase;
import model.Chromosome;
import model.Demand;
import process.InitiatePopulation;
import repository.DemandRepository;
import service.ChromosomeService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"squid:S106", "squid:S6212"})
public class Mainly {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            var connection = MyDatabase.connect();

            Logger.ask("Please input date [yyyy-mm-dd]: ");
            String date = scanner.nextLine();
            List<Demand> demands = DemandRepository.getDemandsByDate(connection, date);

            Logger.systemInfo("Demand on " + date, "MySQL Database");
            Logger.info("Customer IDs: " + Arrays.toString(InitiatePopulation.activeCustomer(demands)));

            Logger.ask("Please input total population to created: ");
            var totalPopulation = scanner.nextInt();
            List<Chromosome> chromosomes = ChromosomeService.buildChromosomeList(totalPopulation, demands);

            chromosomes.forEach(chromosome -> {
                System.out.println("Chromosome " + chromosome.getId());
                chromosome
                    .getVehicles()
                    .forEach(vehicle -> {
                        System.out.println("Vehicle " + vehicle.getPolice_number());
                        System.out.print("[");
                        for (Integer route : vehicle.getRoutes()) {
                            System.out.print(route + " ");
                        }
                        System.out.println("]");
                    });
                System.out.println("=================================");
            });

        } catch (SQLException exception) {
            Logger.error(exception.getMessage());
        }
    }

    private static void printPopulation(java.sql.Connection connection, List<List<Demand>> demandPopulations, AtomicInteger iterations) {
        iterations.set(0);
        demandPopulations.forEach(demandList -> {
            Logger.systemInfo("Population #" + (iterations.incrementAndGet()));

            int[] customerIds = InitiatePopulation.activeCustomer(demandList);
            Logger.info("Customer IDs: " + Arrays.toString(customerIds));

            double[] rangeBetween = InitiatePopulation.activeDistanceBetweenCustomer(customerIds, connection);
            Logger.info("Range between IDs: " + Arrays.toString(rangeBetween));
            Logger.info("Total: " + InitiatePopulation.countTotalDistance(rangeBetween) + " KM");
        });
    }
}
