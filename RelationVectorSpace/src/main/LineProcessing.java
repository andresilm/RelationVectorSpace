package main;

import java.util.List;

public class LineProcessing {
	@SuppressWarnings("static-access")
	public static void processLine(int lineCounter, String line, SharedVectorsCollection relationsVectors, List<String> relationsToBuildFor) throws InterruptedException {
		final int dataCol = 2;
		final int relCol = 0;
		final int predCol = 0;
		final int arg1Col = 1;
		final int arg2Col = 2;
		
		boolean selectedRelationsOnly = relationsToBuildFor != null;
		
		Thread.currentThread().sleep(2000);
		String[] lineSplit = line.split("\\|");
		relationsVectors.increaseArgumentCount("no_rel", 0, Thread.currentThread().getName());
		relationsVectors.increaseArgumentCount("no_rel", 1, Thread.currentThread().getName());
		
		System.err.println("Processed line nr "+ lineCounter+" on thread" +  Thread.currentThread().getName());

	}

	
}
