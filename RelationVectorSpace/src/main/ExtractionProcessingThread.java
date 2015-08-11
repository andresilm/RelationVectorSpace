package main;

import java.util.List;
import java.util.Map;

public class ExtractionProcessingThread extends Thread {
	SharedRelationVectors relationsVectors;
	private boolean waitingNewJob;

	ExtractionProcessingThread(SharedRelationVectors relationsVectors) {
		
		this.relationsVectors = relationsVectors;
		waitingNewJob = true;
		
	}
	
	@Override
	public void start() {
		super.start();
		System.err.println("Starting thread " + this.getName() + ". Waiting for new job.");
	}

	public void processLine(String line) throws InterruptedException {
		this.waitingNewJob = false;
		System.err.println("Processing line in thread " + this.getName());
	
		String[] lineSplit = line.split("\\|");
		System.out.println(lineSplit[0].length());
		sleep(5000);
	
		System.err.println("Thread "+this.getName()+ " finished its job.");
		this.waitingNewJob = true;
	}

	public boolean isWaitingNewJob() {
		return waitingNewJob;
	}

	void setWaitingNewJob(boolean waitingNewJob) {
		this.waitingNewJob = waitingNewJob;
	}

}
