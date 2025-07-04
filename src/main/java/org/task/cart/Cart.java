package org.task.cart;

import org.task.product.Product;
import org.task.product.Shippable;
import org.task.product.ProductValidator;

import java.util.HashMap;


public class Cart {
    double subTotal = 0; // Save Subtotal and weight instead of loop over items everytime
    double weight = 0; // in Kg
    private HashMap<Product, Integer> productQuantity = new HashMap<Product, Integer>();

    public void add(Product product, int quantity) {
        ProductValidator.validateQuantity(product, quantity);
        ProductValidator.validateExpiry(product);

        if (product instanceof Shippable s) {
            weight += s.getWeight() * quantity;
        }

        productQuantity.put(
                product,
                productQuantity.getOrDefault(product, 0) + quantity
        );

        subTotal += product.getPrice() * quantity;
    }


    public void remove(Product product , int quantity) {
        if (quantity > productQuantity.get(product)) {
            throw new IllegalArgumentException("Quantity exceeds the cart quantity.");
        }
        productQuantity.remove(product);
        subTotal -= product.getPrice() * quantity;
    }


    public double calculateShippingFee(double shippingCostPerKg) {
        return weight * shippingCostPerKg;
    }

    // Setters & Getters

    public double getWeight() {
        return weight;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public HashMap<Product, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void clear() {
        weight = 0;
        subTotal = 0;
        productQuantity.clear();
    }
}
