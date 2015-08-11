package main;

import java.util.List;
import java.util.Map;

public class ArgumentCounter implements Runnable {
	SharedRelationVectors relationsVectors;
	private String line;

	ArgumentCounter(SharedRelationVectors relationsVectors) {
		
		this.relationsVectors = relationsVectors;
		
	}
	
	public ArgumentCounter(SharedRelationVectors relationsVectors, String line) {
		this.relationsVectors = relationsVectors;
		this.line = line;
	}

	@Override
	public void run() {
		System.err.println("Starting thread " + Thread.currentThread().getName());
		
	
		
			processLine(this.getLine());
		
		
		System.err.println("Waiting for new job.");
	}
	
	

	public void processLine(String line)   {
		
		System.err.println("Processing line in thread "+ Thread.currentThread().getName());
	
		String[] lineSplit = line.split("\\|");
		System.err.println(lineSplit.length);
		
		
		
	
		System.err.println("Thread  finished its job.");
		
		
	}
	
	


	private String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}




}
