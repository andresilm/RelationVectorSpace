package main;

public class ExtractionProcessingPool extends Thread {
	ExtractionProcessingThread[] threads;
	int numThreads;

	public ExtractionProcessingPool(int numThreads,SharedRelationVectors relationsVectors) {
		threads = new ExtractionProcessingThread[numThreads];
		for (int i = 0; i < numThreads; ++i) {
			threads[i] = new ExtractionProcessingThread(relationsVectors); 
			
		}
		this.numThreads = numThreads;
	}
	
	@Override
	public void start() {
		super.start();
		System.err.println("Pool with thread id: " + this.getName());
		for (ExtractionProcessingThread thread: threads) {
			thread.start(); 
			
		}
	}

	public boolean hasFreeThread() {
		for (ExtractionProcessingThread thread : threads) {
			if (thread.isWaitingNewJob()) {
				return true;
			}
		}
		return false;
	}

	private int getFreeThread() {
		for (int i = 0; i < numThreads; ++i) {
			if (threads[i].isWaitingNewJob()) {
				return i;
			}
		}
		return -1;
	}

	public synchronized boolean assignNewLineToProcess(String line) throws InterruptedException {
		int freeIndex = getFreeThread();
		if (freeIndex > -1) {
			threads[freeIndex].processLine(line);
			return true;
		} else {
			return false;
		}

	}

}
