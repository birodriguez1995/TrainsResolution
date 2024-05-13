package com.example.trains.controller;

import com.example.trains.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrainController implements CommandLineRunner {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Output #1: " + formatOutput(trainService.getRouteDistance("A", "B", "C")));
        System.out.println("Output #2: " + formatOutput(trainService.getRouteDistance("A", "D")));
        System.out.println("Output #3: " + formatOutput(trainService.getRouteDistance("A", "D", "C")));
        System.out.println("Output #4: " + formatOutput(trainService.getRouteDistance("A", "E", "B", "C", "D")));
        System.out.println("Output #5: " + formatOutput(trainService.getRouteDistance("A", "E", "D")));
        System.out.println("Output #6: " + trainService.countTripsWithMaxStops("C", "C", 3));
        System.out.println("Output #7: " + trainService.countTripsWithExactStops("A", "C", 4));
        System.out.println("Output #8: " + trainService.findShortestRoute("A", "C"));
        System.out.println("Output #9: " + trainService.findShortestRoute("B", "B"));
        System.out.println("Output #10: " + trainService.countTripsWithMaxDistance("C", "C", 30));
    }

    private String formatOutput(int result) {
        return result == -1 ? "NO SUCH ROUTE" : String.valueOf(result);
    }
}
