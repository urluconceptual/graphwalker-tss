package org.unibuc.order;

public enum State {
    NONE,
    REQUEST_CREATED,
    APPROVED,
    REJECTED,
    ORDERED_SUPPLIER,
    DELIVERED_STORE,
    PICKED_UP
}