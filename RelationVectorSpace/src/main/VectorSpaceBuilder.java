package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimeUnit;

public class VectorSpaceBuilder {
	private SharedVectorsCollection relationsVectors;
	Scanner input;
	int runningTasks = 0;
	
	VectorSpaceBuilder(String extractionFilename) throws IOException {
		input = new Scanner(new FileReader(new File(extractionFilename)));
		relationsVectors = new SharedVectorsCollection();
		
	}

	

	public void saveAsCSV(String outputFilename) throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(new File(outputFilename)));
	}

	public void create(int numThreads, List<String> relationsToBuild) {
		ScheduledThreadPoolExecutor taskRunner;
	
		numThreads = 4;
		taskRunner =  new ScheduledThreadPoolExecutor(numThreads);
		taskRunner.setMaximumPoolSize(numThreads);
		taskRunner.setCorePoolSize(numThreads);
		
		int lineCounter = 0;
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			System.out.println("Processing line nr " + lineCounter);
			
		while (taskRunner.getActiveCount() == numThreads) {
			//System.err.println("Number of active threads is "+Task.runningTasks + ". Waiting...");
			
		}
			Task lineProcessingTask = new Task(lineCounter,line, getRelationsVectors(),relationsToBuild);
			taskRunner.execute(lineProcessingTask);
			
			
		
			
		
			System.err.println("Number of instantiated tasks :"+Task.runningTasks);
			++lineCounter;

		}
		System.err.println("VectorSpaceBuilder main loop finished");
		System.err.println("Maximum pool size: " + taskRunner.getMaximumPoolSize());
	}

	public SharedVectorsCollection getRelationsVectors() {
		return relationsVectors;
	}

	void setRelationsVectors(SharedVectorsCollection relationsVectors) {
		this.relationsVectors = relationsVectors;
	}



	

}
