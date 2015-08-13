package builder;

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
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import common.Categorizer;
import common.SharedVectorsCollection;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimeUnit;

public class VectorSpaceBuilder {
	private SharedVectorsCollection relationsVectors;
	Scanner input;
	int runningTasks = 0;
	
	
	VectorSpaceBuilder(String extractionFilename) throws IOException {
		input = new Scanner(new FileReader(new File(extractionFilename)));
		relationsVectors = new SharedVectorsCollection();
		
		/*
		 * First invokation to Categorizer i.e. the only time the constructor is invoked.
		 * This is to avoid that the first N threads try to get an instance, and therefore try to call 3 times the constructor
		 */
		Categorizer c = Categorizer.getInstance();
		System.out.println("Categoriea loaded.");
		
	}

	

	public void saveToFile(String outputFilename) throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(new File(outputFilename)));
		System.err.println("Will save " + this.relationsVectors.getVectors().keySet().size() + " relations.");
		for (String key: this.relationsVectors.getVectors().keySet()) {
			System.out.println("Saving on disk '" + key + "'.");
			output.write(key + "|" + this.relationsVectors.getVectors().get(key).toString() + "\n");
		}
		output.close();
	}

	public void create(int numThreads, List<String> relationsToBuild) {
		ScheduledThreadPoolExecutor taskRunner;
		
	//	numThreads = Runtime.getRuntime().availableProcessors()-1;
		taskRunner =  new ScheduledThreadPoolExecutor(numThreads);
		taskRunner.setMaximumPoolSize(numThreads);
		taskRunner.setCorePoolSize(numThreads);
		System.out.println("Will use " + numThreads + " more cores.");
		int lineCounter = 0;
		int taskCounter = 0;
		
		while (input.hasNextLine()) {
			++taskCounter;
			String line = input.nextLine();
			System.err.println("Processing line nr " + lineCounter);
			
		
		//wait some task to finish if the current number of threads reached the maximum specified
		while (taskRunner.getActiveCount() >= numThreads)
			;
			
		
			VSBTask lineProcessingTask = new VSBTask(lineCounter,line, getRelationsVectors(),relationsToBuild);
			taskRunner.execute(lineProcessingTask);
			
			++lineCounter;
			

		}
		
		//wait until all threads finish before writing the results to disk
		
		while (taskRunner.getCompletedTaskCount() < taskCounter)
			System.err.println("Waiting. Completed: " + taskRunner.getCompletedTaskCount() + " of " + taskCounter + " tasks launched.");
			;
		
		System.err.println("Maximum pool size: " + taskRunner.getMaximumPoolSize());
	}

	public SharedVectorsCollection getRelationsVectors() {
		return relationsVectors;
	}

	void setRelationsVectors(SharedVectorsCollection relationsVectors) {
		this.relationsVectors = relationsVectors;
	}



	

}
