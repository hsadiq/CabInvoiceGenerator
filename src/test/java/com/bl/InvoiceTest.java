package com.bl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;


public class InvoiceTest {
        private CabInvoiceGenerator cabInvoiceGenerator;

        @BeforeEach
        public void setup() {
            cabInvoiceGenerator = new CabInvoiceGenerator();
        }


        @Test
        public void testCalculateFare() {
            List<CabInvoiceGenerator.Ride> rides = new ArrayList<>();
            rides.add(new CabInvoiceGenerator.Ride(10.5, 25.0));
            rides.add(new CabInvoiceGenerator.Ride(5.5, 15.0));
            rides.add(new CabInvoiceGenerator.Ride(3.0, 10.0));

            CabInvoiceGenerator.InvoiceSummary summary = cabInvoiceGenerator.calculateFare(rides);

            Assert.assertEquals(3, summary.getTotalRides());
            Assertions.assertEquals(51.5, summary.getTotalFare(), 0.01);
            Assertions.assertEquals(17.17, summary.getAverageFarePerRide(), 0.01);
        }

        @Test
        public void testCalculateFareWithMinimumFare() {
            List<CabInvoiceGenerator.Ride> rides = new ArrayList<>();
            rides.add(new CabInvoiceGenerator.Ride(1.5, 5.0));
            rides.add(new CabInvoiceGenerator.Ride(2.0, 10.0));
            rides.add(new CabInvoiceGenerator.Ride(3.0, 15.0));

            CabInvoiceGenerator.InvoiceSummary summary = cabInvoiceGenerator.calculateFare(rides);

            Assertions.assertEquals(3, summary.getTotalRides());
            Assertions.assertEquals(25.5, summary.getTotalFare(), 0.01);
            Assertions.assertEquals(8.5, summary.getAverageFarePerRide(), 0.01);
        }
    }
