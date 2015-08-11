package main;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length == 3) {
			Scanner input = new Scanner(new FileReader(new File(args[0])));
			BufferedWriter output = new BufferedWriter(new FileWriter(new File(args[1])));
			int numThreads = Integer.valueOf(args[2]);
			SharedRelationVectors relationsVectors = new SharedRelationVectors();
			ExtractionProcessingPool threadsPool = new ExtractionProcessingPool(numThreads,relationsVectors);
			threadsPool.start();
			while (input.hasNextLine()) {
				String line = input.nextLine();
				//while (!threadsPool.hasFreeThread()){System.err.println("Waiting for a free thread.");}
				threadsPool.assignNewLineToProcess(line);
				
			}
			
			
			
		}
		
	}
}
