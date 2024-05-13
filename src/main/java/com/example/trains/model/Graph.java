package com.example.trains.model;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private final Map<String, Map<String, Integer>> adjacencyMap = new HashMap<>();

    public void addRoute(String start, String end, int distance) {
        adjacencyMap.putIfAbsent(start, new HashMap<>());
        adjacencyMap.get(start).put(end, distance);
    }

    public Map<String, Map<String, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public Integer getDistance(String start, String end) {
        return adjacencyMap.getOrDefault(start, new HashMap<>()).get(end);
    }
}
