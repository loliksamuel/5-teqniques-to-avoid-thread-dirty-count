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
    private final String name;
    private final Date date;
    private final ImmutableList<Order> orders;

    public ImmutableObj(int amount, String name, Date date, ImmutableList<Order> orders) {
        this.amount = amount;
        this.name = name;
        this.date = new Date(date.getTime());
        this.orders = orders;
    }


    /// safe getters
    public ImmutableList<Order> getOrders() {
        return orders;//no new cause ImmutableList is immutable
    }

    public int getAmount() {
        return amount;//no new cause int is immutable
    }

    public String getName() {
        return name;//no new cause String is immutable
    }

    public Date getDateTime() {
        return new Date(date.getTime());//new cause Date is mutable
    }




    ///safe setters
    public ImmutableObj addAmount(int amount) {
        return new ImmutableObj(this.amount + amount, name, date, orders);
    }

    public ImmutableObj addOrder(Order newOrder) {
        ImmutableList<Order> newOrderList = new ImmutableList.Builder<Order>()
                .addAll(orders).add(newOrder).build();
        return new ImmutableObj(this.amount, name, date, newOrderList);
    }

    public ImmutableObj setDate(Date date) {
        return new ImmutableObj(this.amount, name, new Date(date.getTime()), orders);
    }

    public ImmutableObj setName(String name) {
        return new ImmutableObj(this.amount, name, date, orders);
    }
}