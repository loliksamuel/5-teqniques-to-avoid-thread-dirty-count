package com.takipi.tests.counters;

import java.util.ArrayList;
import java.util.List;

public class Worker
{
	protected String name ;
	protected int salary;
	protected Worker manager;
	private List<Worker> workers;
	private int level;

	public Worker()
	{

	}

	public Worker(String aName, int aSalary, int level)
	{
		this.name = aName;
		this.salary = aSalary;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Worker getManager() {
		return manager;
	}

	public void setManager(Worker manager) {
		this.manager = manager;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public void addWorker(Worker worker){
		this.workers.add(worker);
	}


	public static void main(String[] args) throws InterruptedException {
		Worker mgrHaim   = new Worker("haim"  , 3000, 1);
		Worker mgrTurkel = new Worker("turkel", 3000, 2);

		List<Worker> workersOfHaim = new ArrayList<Worker>();
		workersOfHaim.add(new Worker("dany", 1000,1));
		workersOfHaim.add(new Worker("sam", 2000,1));
		workersOfHaim.add(new Worker("shay", 1000,2));
		workersOfHaim.add(new Worker("nastia", 2000,2));
		mgrHaim.setWorkers(workersOfHaim);

		List<Worker> workersOfTurkel = new ArrayList<Worker>();
		workersOfTurkel.add(new Worker("dany2", 1000,2));
		workersOfTurkel.add(new Worker("sam2", 2000,3));
		mgrTurkel.setWorkers(workersOfTurkel);

		mgrHaim.setManager(mgrTurkel);
//		Comparator<Manager> byLevel =
//				(Manager o1, Manager o2)->o1.getLevel()-o2.getLevel());

		mgrHaim.getWorkers().sort((Worker m1,Worker m2)->m1.getSalary()-m2.getSalary());

	}
	

}
