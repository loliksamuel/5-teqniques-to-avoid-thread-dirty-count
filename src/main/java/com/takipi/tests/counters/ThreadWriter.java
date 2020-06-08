package com.takipi.tests.counters;

public class ThreadWriter implements Runnable
{
	private final Counter counter;
	
	public ThreadWriter(Counter counter)
	{
		this.counter = counter;
	}
	
	public void run()
	{
		while (true)
		{
			if (Thread.interrupted())
			{
				break;
			}
			
			counter.increment();
		}
	}
}
