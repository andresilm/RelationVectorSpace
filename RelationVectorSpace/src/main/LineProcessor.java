package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class LineProcessor implements Runnable {
	SharedRelationVectors relationsVectors;
	Map<String,List<String>> taxonomyDict;
	private String line;
	Lemmatizer lemmatizer;

	LineProcessor(SharedRelationVectors relationsVectors) {

		this.relationsVectors = relationsVectors;

	}

	public LineProcessor(SharedRelationVectors relationsVectors, String line, Map<String,List<String>> taxonomyDict,Lemmatizer lemmatizer) {
		this.relationsVectors = relationsVectors;
		this.line = line;
		this.taxonomyDict = taxonomyDict;
		this.lemmatizer = lemmatizer;
	}

	@Override
	public void run() {
		//System.err.println("Starting thread " + Thread.currentThread().getName());

		processLine(this.getLine());

		//System.err.println("Waiting for new job.");
	}

	public void processLine(String line) {

		System.err.println("Processing line in thread " + Thread.currentThread().getName());

		String[] lineSplit = line.split("\\|");
		System.err.println(lineSplit.length);

		System.err.println("Thread  finished its job.");

	}

	private String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	private List<String> normalizeArgument(String arg) {
		List<String> categories = new ArrayList();
		for (String key: this.taxonomyDict.keySet()) {
			if (key.contains(arg)) {
				categories.addAll(this.taxonomyDict.get(key));
			}
		}
		
		if (categories.isEmpty()) {//no category found
		categories.add(lemmatizer.getLemma(arg));
		}
		return categories;
	}

}
