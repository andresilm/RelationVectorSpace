package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VSBTask implements Runnable {
	public static int runningTasks = 0;
	 
	SharedVectorsCollection relationsVectors;
	private String line;
	private int lineCounter;

	List<String> relationsToBuildFor;

	VSBTask(int lineCounter,String line, SharedVectorsCollection relationsVectors, List<String> relationsToBuildFor) {
		
		this.relationsVectors = relationsVectors;
		this.line = line;
		this.lineCounter = lineCounter;
		this.relationsToBuildFor = relationsToBuildFor;

	//System.err.println("Created task on thread " +  Thread.currentThread().getName());
	
	}

	@Override
	public void run() {
		++runningTasks;
		/* System.err.println("Starting thread " +
		 Thread.currentThread().getName());
*/
		try {
			LineProcessing.processLine(lineCounter,this.getLine(), relationsVectors, relationsToBuildFor);
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
	


}
