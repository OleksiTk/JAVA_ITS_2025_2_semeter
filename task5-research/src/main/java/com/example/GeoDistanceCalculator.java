package com.example;

public class GeoDistanceCalculator {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: GeoDistanceCalculator <lat1> <lon1> <lat2> <lon2>");
            return;
        }

        double lat1 = Double.parseDouble(args[0]);
        double lon1 = Double.parseDouble(args[1]);
        double lat2 = Double.parseDouble(args[2]);
        double lon2 = Double.parseDouble(args[3]);

        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.printf("Distance: %.3f km%n", distance);
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(dLat / 2), 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
