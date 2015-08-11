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
	ScheduledThreadPoolExecutor executor;
	Scanner input;
	BufferedWriter output;

	VectorSpaceBuilder(String extractionFilename, String outputFilename, int numThreads) throws IOException {
		loadTaxonomyDict();
		executor = new ScheduledThreadPoolExecutor(numThreads);
		input = new Scanner(new FileReader(new File(extractionFilename)));
		output = new BufferedWriter(new FileWriter(new File(outputFilename)));
	}

	public void buildSpaceFor(List<String> relationsToBuild) {

	}

	public void saveAsCSV() {

	}

	public void create() {

	}

	private void loadTaxonomyDict() {

	}

}
