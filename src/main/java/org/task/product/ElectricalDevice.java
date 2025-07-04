package org.task.product;

public class ElectricalDevice extends Product implements Shippable {
    private final double weight;

    public ElectricalDevice(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}
