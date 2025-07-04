package org.task.service;

import org.task.cart.Cart;
import org.task.customer.Customer;
import org.task.product.Product;
import org.task.product.Expirable;
import org.task.product.Shippable;
import org.task.product.ProductValidator;

import java.util.List;
import java.util.Map;

public class CheckOutService {

    public void processCheckout(Customer customer, ShippingService shippingService) throws Exception {
        Cart cart = customer.getCart();
        Map<Product, Integer> productQuanMap = cart.getProductQuantity();
        List<Shippable> shippableItems = cart.getProductQuantity().keySet().stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .toList();


        if (productQuanMap.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        // Final checks
        for (Map.Entry<Product, Integer> entry : productQuanMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            ProductValidator.validateQuantity(product, quantity);
            ProductValidator.validateExpiry(product);
        }

        // Cost Calc
        double shipping = cart.calculateShippingFee(shippingService.getCost());
        double subtotal = cart.getSubTotal();

        double total = shipping + subtotal;

        // Check balance
        if (customer.getBalance() < total) {
            throw new IllegalArgumentException("Not enough balance.");
        }

        // Deduct stock
        for (Map.Entry<Product, Integer> entry : productQuanMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.decrementQuantity(quantity);
        }

        // Deduct Balance and ship items
        customer.deductBalance(total);
        if (shippableItems.size() > 0) {
            shippingService.ship(shippableItems);
        }


        // Print shipment notice
        System.out.println("** Shipment notice **");
        for (Map.Entry<Product, Integer> entry : productQuanMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Shippable) {
                System.out.printf("%dx %s %.0fg%n", quantity, product.getName(),
                        ((Shippable) product).getWeight() * 1000 * quantity );
            }
        }
        if (cart.getWeight() > 0)
            System.out.printf("Total package weight %.1fkg%n", cart.getWeight());

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : productQuanMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            System.out.printf("%dx %s %.0f%n", quantity, product.getName(),
                    product.getPrice() * quantity);
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n", total);
        System.out.printf("Updated Customer Balance %.0f%n", customer.getBalance());


        cart.clear(); // Clear cart
    }
}
