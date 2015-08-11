package main;

import java.util.List;
import java.util.Map;

public class ExtractionProcessingThread extends Thread {
	SharedRelationVectors relationsVectors;
	private boolean waitingNewJob = true;

	ExtractionProcessingThread(SharedRelationVectors relationsVectors) {
		System.err.println("Starting thread");
		this.relationsVectors = relationsVectors;
	}

	public void processLine(String line) {
		System.err.println("Processing line");
		this.waitingNewJob = false;
		String[] lineSplit = line.split("\\|");
		System.out.println(lineSplit[0].length());
		this.waitingNewJob = true;
		System.err.println("Thread finished job");
	}

	public boolean isWaitingNewJob() {
		return waitingNewJob;
	}

	void setWaitingNewJob(boolean waitingNewJob) {
		this.waitingNewJob = waitingNewJob;
	}

}
