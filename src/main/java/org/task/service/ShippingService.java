package org.task.service;

import org.task.product.Shippable;
import java.util.List;

public class ShippingService {
    private double costPerKg;

    public ShippingService(double costPerKg) {
        this.costPerKg = costPerKg;
    }

    public void ship(List<Shippable> items) {
        System.out.println("Shipping Service received " + items.size() + " items");
    }

    // Setters & Getters

    public double getCost() {
        return costPerKg;
    }

}