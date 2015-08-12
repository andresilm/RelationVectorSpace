package main;

import java.util.List;

public class LineProcessor {
	@SuppressWarnings("static-access")
	public static void processLine(String line, SharedVectorsCollection relationsVectors, List<String> relationsToBuildFor) throws InterruptedException {
		boolean selectedRelationsOnly = relationsToBuildFor != null;
		
		Thread.currentThread().sleep(3000);
		String[] lineSplit = line.split("\\|");
		System.err.println("LineProcessor stub");

	}

	
}
