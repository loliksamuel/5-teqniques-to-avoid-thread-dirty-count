package com.takipi.tests.counters.immutables;

import java.util.Date;
import com.google.common.collect.ImmutableList;

/*
An immutable object is an object whose internal state remains constant after it has been entirely created.

This means that :
 1. public API of an immutable object guarantees us that it will behave in the same way during its whole lifetime.
 2. thread safe
 3. easily scalable

 but it has some disadvantages:
 1. too many new resources ar egenerated

 so do not use on write intessive app.

* u can use:
* 1. https://github.com/immutables/immutables
* 2. https://github.com/rzwitserloot/lombok
* 3. use scala/kotlin
* 4. your own implemantation using final class and private final fields
* */
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