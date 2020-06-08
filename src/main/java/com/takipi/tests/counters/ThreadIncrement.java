package com.takipi.tests.counters;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadIncrement implements Runnable {
    static int counter = 1; // a global counter

    static ReentrantLock counterLock = new ReentrantLock(true); // enable fairness policy

    static void incrementCounter(){
        //counterLock.lock();

        try{
            System.out.println(Thread.currentThread().getName() + ": " + counter);
            counter++;
        }finally{// Always good practice to enclose locks in a try-finally block
         //    counterLock.unlock();
        }
     }

    @Override
    public void run() {
        while(counter<10000){
            incrementCounter();
        }
    }
    /*

question: 2 threads are incrementing an integer variable (initially 0) 100 times each without synchronization,
what would be the possible minimum and maximum value? Justify your answer.

answer:
max = var + 200
min = var + 2

The theoretical maximum is counter == 200.
This occurs in any situation where there are no race conditions on the reading and writing of counter.
For example, in the case when Thread 0 performs all of its iterations before Thread 1 performs all of its iterations
--------
THREAD 0
--------

  ___     ___          _____
 |   |   |   |        |     |             each "staple" represents a
 |   |   |   |   ...  |     |       <---- read, increment, write
c=0 c=1 c=1 c=2      c=99 c=100
------------------------------------------------------------
                                 c=100 c=101     c=199 c=200
                                   |     |   ...   |     |
                                   |_____|         |_____|

--------
THREAD 1
--------



As for determining the theoretical minimum (which is the hard part of this problem)
consider the following worst-case thread scheduling scenario:

Thread 0 read c == 0
Thread 1 read c == 0
Thread 1 increment and write such that c == 1
Thread 1 read, increment and write x98 more times ending up with c == 99. Note that there is one more iteration that Thread 1 needs to take before its work is complete.
Thread 0 increment and write resulting in c == 1.
Thread 1 perform last read c == 1
Thread 0 read, increment, and write remaining x99 times ending up with c == 99. Thread 1 has finished its iterations.
Thread 1 perform final increment and write resulting in c == 2.


    without locks it can end with 2 or 200
    Thread-1: 1
    Thread-0: 1
    Thread-0: 3
    Thread-0: 4
    Thread-1: 2
    Thread-0: 5
    Thread-1: 6
    Thread-1: 8
    Thread-1: 9
    Thread-0: 7
    with locks it prints:(always ends with 100)
    Thread-0: 1
    Thread-1: 2
    Thread-0: 3
    Thread-1: 4
    Thread-0: 5
    Thread-1: 6
    Thread-0: 7
    Thread-1: 8
    Thread-0: 9
    .
    .
    .
    Thread-1: 100
    */

}