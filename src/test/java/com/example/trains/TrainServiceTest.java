package com.example.trains;

import com.example.trains.service.TrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainServiceTest {

	private TrainService trainService;

	@BeforeEach
	public void setUp() {
		trainService = new TrainService();
	}

	@Test
	public void testGetRouteDistance() {
		assertEquals(9, trainService.getRouteDistance("A", "B", "C"));
		assertEquals(5, trainService.getRouteDistance("A", "D"));
		assertEquals(13, trainService.getRouteDistance("A", "D", "C"));
		assertEquals(22, trainService.getRouteDistance("A", "E", "B", "C", "D"));
		assertEquals(-1, trainService.getRouteDistance("A", "E", "D")); // NO SUCH ROUTE
	}

	@Test
	public void testCountTripsWithMaxStops() {
		assertEquals(2, trainService.countTripsWithMaxStops("C", "C", 3));
	}

	@Test
	public void testCountTripsWithExactStops() {
		assertEquals(3, trainService.countTripsWithExactStops("A", "C", 4));
	}

	@Test
	public void testFindShortestRoute() {
		assertEquals(9, trainService.findShortestRoute("A", "C"));
		assertEquals(9, trainService.findShortestRoute("B", "B"));
	}

	@Test
	public void testCountTripsWithMaxDistance() {
		assertEquals(7, trainService.countTripsWithMaxDistance("C", "C", 30));
	}
}
