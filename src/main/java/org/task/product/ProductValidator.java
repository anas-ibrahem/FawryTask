package org.task.product;

public class ProductValidator {

    public static void validateQuantity(Product product, int requestedQuantity) {
        if (requestedQuantity > product.getQuantity()) {
            throw new IllegalArgumentException(
                    "Requested quantity of " + product.getName() + " exceeds available stock (" + product.getQuantity() + ")."
            );
        }
    }

    public static void validateExpiry(Product product) {
        if (product instanceof Expirable expirable) {
            if (expirable.isExpired()) {
                throw new IllegalArgumentException(
                        "Product " + product.getName() + " is expired."
                );
            }
        }
    }
}
