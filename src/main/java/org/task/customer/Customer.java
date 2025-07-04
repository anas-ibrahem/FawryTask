package org.task.customer;

import org.task.cart.Cart;

public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name , double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Not enough balance.");
        }
        balance -= amount;
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

}
