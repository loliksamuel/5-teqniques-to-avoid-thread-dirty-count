package com.takipi.tests.counters.implementations;

import com.takipi.tests.counters.Counter;

public class Volatile implements Counter
{
	private volatile long counter;//the volatile modifier guarantees that any thread that reads a field will see the most recently written value
	
	public long getCounter()
	{
		return counter;
	}
	
	public void increment() 
	{
		++counter;
	}
}