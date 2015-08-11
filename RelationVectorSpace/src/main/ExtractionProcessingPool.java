package main;

public class ExtractionProcessingPool {
	ExtractionProcessingThread[] threads;
	int numThreads;

	public ExtractionProcessingPool(int numThreads,SharedRelationVectors relationsVectors) {
		threads = new ExtractionProcessingThread[numThreads];
		for (ExtractionProcessingThread thread: threads) {
			thread = new ExtractionProcessingThread(relationsVectors); 
			thread.start();
		}
		this.numThreads = numThreads;
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

	public boolean assignNewLineToProcess(String line) {
		int freeIndex = getFreeThread();
		if (freeIndex > -1) {
			threads[freeIndex].processLine(line);
			return true;
		} else {
			return false;
		}

	}

}
