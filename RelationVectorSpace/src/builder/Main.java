package builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length == 3) {
			String inputFilename = args[0];
			String outputFilename = args[1];
			int numThreads = Integer.valueOf(args[2]);

			List<String> relationsToBuild = null;
			
			if (args.length == 4 && !args[3].equals("")) {

				relationsToBuild = loadRelationsList(args[3]);
			}

			System.out.println("Will create the relation vector space for "
					+ ((relationsToBuild == null || relationsToBuild.isEmpty()) ? "all the " : relationsToBuild.size() + " selected")
					+ " relations, using " + inputFilename + " as source.");
		

			VectorSpaceBuilder spaceBuilder = new VectorSpaceBuilder(inputFilename);

			System.out.println("Vector space creation started.");
			spaceBuilder.create(numThreads, relationsToBuild);

			System.out.println("Saving relations vectors to file " + outputFilename);
			spaceBuilder.saveToFile(outputFilename);

			System.out.println("Done.");

		}

	}

	private static List<String> loadRelationsList(String string) {
		return null;
		// TODO Auto-generated method stub

	}

}
