package main;

import config.CustomLogger;
import model.Demand;
import process.InitiatePopulation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = CustomLogger.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var iterations = new AtomicInteger();

        ask("Please input date: ");
        List<Demand> demands = InitiatePopulation.getDemandsByDate(scanner.nextLine());

        important("Demand on " + demands.get(0).getDate().toString());
        demands.forEach(demand -> process(demand.toString()));

        ask("Please input total population to created: ");
        List<List<Demand>> demandPopulations = InitiatePopulation.createDemandPopulations(demands, scanner.nextInt());

        important("Demand Populations");

        iterations.set(0);
        demandPopulations.forEach(demandList -> {
            important("Population #" + (iterations.incrementAndGet()));

            demandList.forEach(demand -> process(demand.getCustomerId().toString()));
            important("Total: " + BigDecimal.valueOf(InitiatePopulation.countTotalDistance(demandList)).setScale(0,
                RoundingMode.CEILING) + " KM");
            divider();
        });
    }

    private static void ask(String message) {
        logger.info(message);
    }

    private static void process(String message) {
        logger.warning(message);
    }

    private static void important(String message) {
        logger.severe(message);
    }

    private static void divider() {
        important("========================================================================================");
    }
}
