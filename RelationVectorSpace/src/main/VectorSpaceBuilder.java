package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class VectorSpaceBuilder {
	Scanner source;
	List<String> relationsToBuild;
	SharedRelationVectors relationsVectors = new SharedRelationVectors();
	Scanner input;
	BufferedWriter output;

	VectorSpaceBuilder(String extractionFilename) throws IOException {
		loadTaxonomyDict();
		
		input = new Scanner(new FileReader(new File(extractionFilename)));
		
	}

	public void buildSpaceFor(List<String> relationsToBuild) {

	}

	public void saveAsCSV(String outputFilename) throws IOException {
		output = new BufferedWriter(new FileWriter(new File(outputFilename)));
	}

	public void create(int numThreads) {
		ScheduledThreadPoolExecutor executor;
		executor = new ScheduledThreadPoolExecutor(numThreads);
	}

	private void loadTaxonomyDict() throws FileNotFoundException {
		Scanner taxFile = new Scanner(new FileReader(new File("resources/taxonomy.txt")));
	}

}
