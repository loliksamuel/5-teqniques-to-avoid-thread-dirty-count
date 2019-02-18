package com.takipi.tests.counters.implementations.immutables;

//immutableOrder
public final class Order {

    private final int id;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
