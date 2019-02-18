package com.takipi.tests.counters.implementations.immutables;

import java.util.Date;
import com.google.common.collect.ImmutableList;

public final class ImmutableObj {

    private final int amount;
    private final Date date;
    private final ImmutableList<Order> orders;

    public ImmutableObj(int amount, Date date, ImmutableList<Order> orders) {
        this.amount = amount;
        this.date = date;
        this.orders = orders;
    }

    public ImmutableList<Order> getOrders() {
        return orders;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDateTime() {
        return date;
    }

    public ImmutableObj addAmount(int amount) {
        return new ImmutableObj(this.amount + amount, date, orders);
    }

    public ImmutableObj addOrder(Order newOrder) {
        ImmutableList<Order> newOrderList = new ImmutableList.Builder<Order>()
                .addAll(orders).add(newOrder).build();
        return new ImmutableObj(this.amount, date, newOrderList);
    }

    public ImmutableObj setDate(Date date) {
        return new ImmutableObj(this.amount, new Date(date.getTime()), orders);
    }
}