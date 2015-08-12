package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Categorizer {
	static Categorizer singulete;
	
	Lemmatizer lemmatizer;
	Map<String,List<String>> taxonomyDict;
	
	Categorizer() throws FileNotFoundException {
		loadTaxonomyDict();
		lemmatizer = Lemmatizer.getInstance();
	}
	
	public Categorizer getInstance() throws FileNotFoundException
	{
		if (singulete == null) {
			singulete = new Categorizer();
		}
		return singulete;
	}
	private void loadTaxonomyDict() throws FileNotFoundException {
		Scanner taxFile = new Scanner(new FileReader(new File("resources/taxonomy.txt")));
	}
	
	public List<String> normalizeArgument(String arg) {
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
