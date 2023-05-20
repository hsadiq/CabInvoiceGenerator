package com.bl;

import java.util.ArrayList;
import java.util.List;

public class CabInvoiceGenerator {
    private static final double COST_PER_KM = 1.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    public static class Ride {
        private final double distance;
        private final double time;

        public Ride(double distance, double time) {
            this.distance = distance;
            this.time = time;
        }

        public double getDistance() {
            return distance;
        }

        public double getTime() {
            return time;
        }
    }

    public static class InvoiceSummary {
        private final int totalRides;
        private final double totalFare;
        private final double averageFarePerRide;

        public InvoiceSummary(int totalRides, double totalFare) {
            this.totalRides = totalRides;
            this.totalFare = totalFare;
            this.averageFarePerRide = totalFare / totalRides;
        }

        public int getTotalRides() {
            return totalRides;
        }

        public double getTotalFare() {
            return totalFare;
        }

        public double getAverageFarePerRide() {
            return averageFarePerRide;
        }
    }

    public static InvoiceSummary calculateFare(List<Ride> rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            double fare = ride.getDistance() * COST_PER_KM + ride.getTime() * COST_PER_MINUTE;
            totalFare += Math.max(fare, MINIMUM_FARE);
        }
        int totalRides = rides.size();
        return new InvoiceSummary(totalRides, totalFare);
    }

    public static void main(String[] args) {
        // Example rides
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(10.5, 25.0));
        rides.add(new Ride(5.5, 15.0));
        rides.add(new Ride(3.0, 10.0));

        InvoiceSummary summary = calculateFare(rides);

        System.out.println("Total number of rides: " + summary.getTotalRides());
        System.out.println("Total fare: Rs. " + summary.getTotalFare());
        System.out.println("Average fare per ride: Rs. " + summary.getAverageFarePerRide());
    }
}
