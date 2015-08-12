package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Task implements Runnable {
	public static int runningTasks=0;
	
	SharedRelationVectors relationsVectors;
	
	private String line;
	

	Task(String line, SharedRelationVectors relationsVectors) {

		this.relationsVectors = relationsVectors;
		this.line = line;
		
		++runningTasks;

	}


	@Override
	public void run() {
		//System.err.println("Starting thread " + Thread.currentThread().getName());

		LineProcessor.processLine(this.getLine());

		//System.err.println("Waiting for new job.");
	
		--this.runningTasks;//will be destroyed soon.

}
	

	private String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	


}
