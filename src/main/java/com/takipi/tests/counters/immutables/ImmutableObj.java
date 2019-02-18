package com.takipi.tests.counters.immutables;

import java.util.Date;
import com.google.common.collect.ImmutableList;

/*
An immutable object is an object whose internal state remains constant after it has been entirely created.
1. make class final
2. make all fields final and private
3. replace void setXXX(value)  methods  with ClassXXX setXXX(value) that create copy of ClassXXX
4. copy mutable fields on constructor
5. recommended to use immutable collections or scala/kotlin
6. or libraries with annotations
* 1. https://github.com/immutables/immutables
* 2. https://github.com/rzwitserloot/lombok
* 3. autovalue


This means that :
 1. public API of an immutable object guarantees us that it will behave in the same way during its whole lifetime.
 2. thread safe/no side effects/avoid coupling/avoid shared mutability
 3. easier scale
 4. easier to test

 but it has some disadvantages:
 1. too many new resources are generated

 so do not use on write intensive app.


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