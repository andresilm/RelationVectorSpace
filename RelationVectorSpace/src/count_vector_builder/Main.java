package count_vector_builder;

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
		if (args.length >= 4) {
			String inputFilename = args[0];
			String outputFilename = args[1];
			String featuresFilename = args[2];
			int numThreads = Integer.valueOf(args[3]);

			List<String> relationsToBuild = null;
			
			if (args.length == 5 && !args[4].equals("")) {

				relationsToBuild = loadRelationsList(args[4]);
			}

			System.out.println("Will create the relation vector space for "
					+ ((relationsToBuild == null || relationsToBuild.isEmpty()) ? "all the " : relationsToBuild.size() + " selected")
					+ " relations, using " + inputFilename + " as source.");
		

			CountVectorsBuilder spaceBuilder = new CountVectorsBuilder(inputFilename);

			System.out.println("Vector space creation started.");
			spaceBuilder.create(numThreads, relationsToBuild);
			
			
			System.out.println("Saving relations vectors to file " + outputFilename);
			spaceBuilder.saveCountVectorsToFile(outputFilename);
			System.out.println("Saving features to file " + featuresFilename);
			spaceBuilder.saveFeaturesToFiles(featuresFilename);

			System.out.println("Done.");

		}
		else {
			System.out.println("Usage: framex_extraction_file	occurrences_count_output	features_output number_threads	[list_of_relations_to_count_file]");
		}

	}

	private static List<String> loadRelationsList(String string) {
		return null;
		// TODO Auto-generated method stub

	}

}
