package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task implements Runnable {
	public static int runningTasks = 0;
	SharedVectorsCollection relationsVectors;
	private String line;
	private int lineCounter;

	List<String> relationsToBuildFor;

	Task(int lineCounter,String line, SharedVectorsCollection relationsVectors, List<String> relationsToBuildFor) {
		
		this.relationsVectors = relationsVectors;
		this.line = line;
		this.lineCounter = lineCounter;
		this.relationsToBuildFor = relationsToBuildFor;

	System.err.println("Created task on thread " +  Thread.currentThread().getName());
	++runningTasks;
	}

	@Override
	public void run() {
		
		 System.err.println("Starting thread " +
		 Thread.currentThread().getName());

		try {
			processLine(this.lineCounter,this.getLine(), relationsVectors, relationsToBuildFor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	//	System.err.println("Task finished on thread " + Thread.currentThread().getName());

		--runningTasks;// will be destroyed soon.
		

	}

	private String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public void processLine(int lineCounter,String line, SharedVectorsCollection relationsVectors, List<String> relationsToBuildFor) throws InterruptedException {
		boolean selectedRelationsOnly = relationsToBuildFor != null;
		
		Thread.currentThread().sleep(3000);
		String[] lineSplit = line.split("\\|");
		System.err.println("Processed line nr "+ lineCounter+" on thread" +  Thread.currentThread().getName());

	}

}
