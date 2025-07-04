package org.task;

import org.task.customer.Customer;
import org.task.product.Biscuits;
import org.task.product.Cheese;
import org.task.product.MobileScratchCard;
import org.task.product.TV;
import org.task.service.CheckOutService;
import org.task.service.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CheckOutService checkout = new CheckOutService();
        ShippingService aramex = new ShippingService(20);

        // Test 1: Successful checkout
        try {
            System.out.println("Running Test 1: Successful checkout");
            Cheese cheese = new Cheese("Cheese", 100.0, 5, 0.2, LocalDate.of(2025, 7, 30));
            Customer customer = new Customer("Anas", 1000.0);
            customer.getCart().add(cheese, 2);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 1 passed\n");
        } catch (Exception e) {
            System.out.println("Test 1 failed: " + e.getMessage() + "\n");
        }

        // Test 2: Quantity exceeds stock
        try {
            System.out.println("Running Test 2: Quantity exceeds stock");
            Cheese cheese = new Cheese("Cheese", 100.0, 3, 0.2, LocalDate.of(2025, 7, 30));
            Customer customer = new Customer("Anas", 1000.0);
            customer.getCart().add(cheese, 5); // more than available
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 2 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 2 passed: " + e.getMessage() + "\n");
        }

        // Test 3: Product is expired
        try {
            System.out.println("Running Test 3: Expired product");
            Biscuits biscuits = new Biscuits("Biscuits", 50.0, 5, 0.5, LocalDate.of(2023, 1, 1));
            Customer customer = new Customer("Anas", 1000.0);
            customer.getCart().add(biscuits, 1);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 3 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 3 passed: " + e.getMessage() + "\n");
        }

        // Test 4: Insufficient balance
        try {
            System.out.println("Running Test 4: Insufficient balance");
            TV tv = new TV("TV", 3000.0, 5, 10.0);
            Customer customer = new Customer("Anas", 100.0); // too low
            customer.getCart().add(tv, 1);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 4 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 4 passed: " + e.getMessage() + "\n");
        }

        // Test 5: Empty cart
        try {
            System.out.println("Running Test 5: Empty cart");
            Customer customer = new Customer("Anas", 1000.0);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 5 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 5 passed: " + e.getMessage() + "\n");
        }

        // Test 6: Only shippable items
        try {
            System.out.println("Running Test 6: Only shippable items");
            TV tv = new TV("TV", 500.0, 2, 5.0);
            Cheese cheese = new Cheese("Cheese", 50.0, 5, 0.3, LocalDate.of(2025, 8, 1));
            Customer customer = new Customer("Anas", 2000.0);
            customer.getCart().add(tv, 1);
            customer.getCart().add(cheese, 2);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 6 passed\n");
        } catch (Exception e) {
            System.out.println("Test 6 failed: " + e.getMessage() + "\n");
        }

        // Test 7: Only non-shippable items
        try {
            System.out.println("Running Test 7: Only non-shippable items");
            MobileScratchCard card = new MobileScratchCard("Card", 20.0, 10);
            Customer customer = new Customer("Anas", 500.0);
            customer.getCart().add(card, 3);
            checkout.processCheckout(customer, aramex);
            System.out.println("Test 7 passed\n");
        } catch (Exception e) {
            System.out.println("Test 7 failed: " + e.getMessage() + "\n");
        }

        // Test 8: All types of items — success
        try {
            System.out.println("Running Test 8: Mixed items (all types) — successful checkout");

            Cheese cheese = new Cheese("Cheese", 100.0, 5, 0.2, LocalDate.of(2025, 8, 1)); // Shippable + Expirable
            Biscuits biscuits = new Biscuits("Biscuits", 150.0, 3, 0.7, LocalDate.of(2025, 9, 1)); // Shippable + Expirable
            TV tv = new TV("Samsung TV", 3000.0, 2, 10.0); // Shippable only
            MobileScratchCard card = new MobileScratchCard("Vodafone Card", 30.0, 10); // Non-shippable

            Customer customer = new Customer("Anas", 10000.0); // Sufficient balance

            customer.getCart().add(cheese, 2);
            customer.getCart().add(biscuits, 1);
            customer.getCart().add(tv, 1);
            customer.getCart().add(card, 5);

            checkout.processCheckout(customer, aramex);
            System.out.println("Test 8 passed\n");
        } catch (Exception e) {
            System.out.println("Test 8 failed: " + e.getMessage() + "\n");
        }

    }
}
