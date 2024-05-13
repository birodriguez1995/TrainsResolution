package com.example.trains.service;

import com.example.trains.model.Graph;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainService {
    private final Graph graph;

    public TrainService() {
        graph = new Graph();
        initializeGraph();
    }

    private void initializeGraph() {
        graph.addRoute("A", "B", 5);
        graph.addRoute("B", "C", 4);
        graph.addRoute("C", "D", 8);
        graph.addRoute("D", "C", 8);
        graph.addRoute("D", "E", 6);
        graph.addRoute("A", "D", 5);
        graph.addRoute("C", "E", 2);
        graph.addRoute("E", "B", 3);
        graph.addRoute("A", "E", 7);
    }

    public int getRouteDistance(String... towns) {
        int distance = 0;
        for (int i = 0; i < towns.length - 1; i++) {
            Integer routeDistance = graph.getDistance(towns[i], towns[i + 1]);
            if (routeDistance == null) {
                return -1; // NO SUCH ROUTE
            }
            distance += routeDistance;
        }
        return distance;
    }

    public int countTripsWithMaxStops(String start, String end, int maxStops) {
        return countTrips(start, end, maxStops, true);
    }

    public int countTripsWithExactStops(String start, String end, int exactStops) {
        return countTrips(start, end, exactStops, false);
    }

    private int countTrips(String start, String end, int stops, boolean maxStops) {
        if (stops < 0) {
            return 0;
        }
        if (stops == 0 && start.equals(end)) {
            return 1;
        }
        int count = 0;
        Map<String, Integer> neighbors = graph.getAdjacencyMap().get(start);
        if (neighbors != null) {
            for (String neighbor : neighbors.keySet()) {
                count += countTrips(neighbor, end, stops - 1, maxStops);
            }
        }
        return maxStops ? count : (start.equals(end) ? 1 : 0);
    }

    public int findShortestRoute(String start, String end) {
        Set<String> visited = new HashSet<>();
        return findShortestRoute(start, end, visited);
    }

    private int findShortestRoute(String start, String end, Set<String> visited) {
        if (start.equals(end) && !visited.isEmpty()) {
            return 0;
        }
        visited.add(start);
        int shortestDistance = Integer.MAX_VALUE;
        Map<String, Integer> neighbors = graph.getAdjacencyMap().get(start);
        if (neighbors != null) {
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                if (!visited.contains(neighbor.getKey()) || neighbor.getKey().equals(end)) {
                    int distance = neighbor.getValue() + findShortestRoute(neighbor.getKey(), end, new HashSet<>(visited));
                    shortestDistance = Math.min(shortestDistance, distance);
                }
            }
        }
        return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
    }

    public int countTripsWithMaxDistance(String start, String end, int maxDistance) {
        return countTripsWithDistance(start, end, maxDistance, 0);
    }

    private int countTripsWithDistance(String start, String end, int maxDistance, int currentDistance) {
        if (currentDistance >= maxDistance) {
            return 0;
        }
        int count = 0;
        Map<String, Integer> neighbors = graph.getAdjacencyMap().get(start);
        if (neighbors != null) {
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                int newDistance = currentDistance + neighbor.getValue();
                if (newDistance < maxDistance) {
                    if (neighbor.getKey().equals(end)) {
                        count++;
                    }
                    count += countTripsWithDistance(neighbor.getKey(), end, maxDistance, newDistance);
                }
            }
        }
        return count;
    }
}
