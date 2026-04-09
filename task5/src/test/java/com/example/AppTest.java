package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        double distance = GeoDistanceCalculator.calculateDistance(50.4501, 30.5234, 50.4547, 30.5238);
        assertTrue(distance >= 0);
    }
}
