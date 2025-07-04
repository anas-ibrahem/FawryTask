package org.task.product;

import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
}