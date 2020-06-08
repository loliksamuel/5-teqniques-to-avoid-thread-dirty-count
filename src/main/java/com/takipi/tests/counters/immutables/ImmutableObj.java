package com.takipi.tests.counters.immutables;

import java.util.Date;
import com.google.common.collect.ImmutableList;

/*
An immutable object is an object whose internal state remains constant after it has been entirely created.
1. make class final()to avoid inheritance)
2. make all fields final and private
3. copy mutable fields on constructor(like date)
4. use safe getters on mutable fields(using clone)
5. use safe setters : replace void setXXX(value)  methods  with ClassXXX setXXX(value) that create copy of ClassXXX
6. recommended to use immutable collections or scala/kotlin
7. or libraries with annotations
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

//1. make class final
public final class ImmutableObj {

    //2. make fields final and private
    private final int amount;
    private final String name;
    private final Date date;//note:  this field is mutable ...
    private final ImmutableList<Order> orders;//6. using immutable fields from guava

    public ImmutableObj(int amount, String name, Date date, ImmutableList<Order> orders) {
        this.amount = amount;
        this.name = name;
        this.date = new Date(date.getTime());//3. copiing mutable fields
        this.orders = orders;
    }


    //4. use safe getters on mutable fields
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
        return (Date)date.clone();//clone cause Date is mutable
    }




    ///5. use safe setters
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
        return new ImmutableObj(this.amount, name, date , orders);
    }
}