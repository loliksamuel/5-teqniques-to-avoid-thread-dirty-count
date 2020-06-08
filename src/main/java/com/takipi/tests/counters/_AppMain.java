package com.takipi.tests.counters;

import com.takipi.tests.counters.implementations.Adder;
import com.takipi.tests.counters.implementations.Atomic;
import com.takipi.tests.counters.implementations.Dirty;
import com.takipi.tests.counters.implementations.RWLock;
import com.takipi.tests.counters.implementations.Synchronized;
import com.takipi.tests.counters.implementations.Volatile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _AppMain
{
	public static long TARGET_NUMBER 	= 10000000l;//100000000l;
	public static int  THREADS 			= 10;
	public static int  ROUNDS 			= 10;
	private static String COUNTER 		= Counters.DIRTY.toString();
	//private static String COUNTER 		= Counters.SYNCHRONIZED.toString();

	private static ExecutorService es;
	
	private static int round;
	private static long start;
	
	private static Boolean[] rounds;
	
	private static enum Counters
	{
		DIRTY,
		VOLATILE,
		SYNCHRONIZED,
		RWLOCK,
		ATOMIC,
		ADDER,
		STAMPED,
		OPTIMISTIC
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Task One: 2 threads without synchronization mechanism are incrementing an integer variable   10,000 times each, "
				+"\n" +
				"what would be the possible minimum and maximum value? "
				+"\n" +
				"answer: between 2 and 20,000  (see below)");
		ThreadIncrement te = new ThreadIncrement();
		Thread thread1 = new Thread(te);
		Thread thread2 = new Thread(te);
		thread1.start();
		thread2.start();
		Thread.sleep(2000);



		if (args.length > 0)
			COUNTER = args[0];
		if (args.length > 1)
			THREADS = Integer.valueOf(args[1]);
		if (args.length > 2)
			ROUNDS = Integer.valueOf(args[2]);
		if (args.length > 3)
			TARGET_NUMBER = Long.valueOf(args[3]);

		rounds = new Boolean[ROUNDS];
		
		System.out.println("\n\nTask Two: "+THREADS+" threads Using " + COUNTER + " mechanism  are incrementing an integer variable " + TARGET_NUMBER +
				" times with "+ROUNDS+" rounds."
				+"\n" +
						"which mechanism will be the fastest?"
				+"\n" +
						"answer: DIRTY");
		
		for (round = 0; round < ROUNDS; round++)
		{
			rounds[round] = Boolean.FALSE;
			
			Counter counter = getCounter();
			
			es = Executors.newFixedThreadPool(THREADS);
			
			start = System.currentTimeMillis();
			
			for (int j = 0; j < THREADS; j+=2)
			{	
				es.execute(new ThreadReader(counter));
				es.execute(new ThreadWriter(counter));
			}
			
			try
			{
				es.awaitTermination(10, TimeUnit.MINUTES);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static Counter getCounter()
	{
		Counters counterTypeEnum = Counters.valueOf(COUNTER);
		
		switch (counterTypeEnum)
		{
			case ADDER:
				return new Adder();
			case ATOMIC:
				return new Atomic();
			case DIRTY:
				return new Dirty();
			case RWLOCK:
				return new RWLock();
			case SYNCHRONIZED:
				return new Synchronized();
			case VOLATILE:
				return new Volatile();
		}
		
		return null;
	}
	
	public static void publish(long end)
	{
		synchronized (rounds[round])
		{
			if (rounds[round] == Boolean.FALSE)
			{
				System.out.println("round-"+round+": "+(end-start) + " seconds.");
				
				rounds[round] = Boolean.TRUE;
				
				es.shutdownNow();
			}
		}
	}
}
