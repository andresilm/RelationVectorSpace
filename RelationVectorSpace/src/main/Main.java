package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length == 3) {
			Scanner input = new Scanner(new FileReader(new File(args[0])));
			BufferedWriter output = new BufferedWriter(new FileWriter(new File(args[1])));
			int numThreads = Integer.valueOf(args[2]);
			SharedRelationVectors relationsVectors = new SharedRelationVectors();
			
			ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(numThreads);

			while (input.hasNextLine()) {

				String line = input.nextLine();
				System.err.println("line = " + line);
				ArgumentCounter argCounter = new ArgumentCounter(relationsVectors, line);

				
				executor.execute(argCounter);

				
			}

		}

	}
}
