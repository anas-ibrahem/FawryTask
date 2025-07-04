package org.task.product;

import java.time.LocalDate;

public class Food extends Product implements Expirable, Shippable {
    private final double weight;
    private final LocalDate expiryDate;

    public Food(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
