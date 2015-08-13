package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import builder.LineProcessing;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

public class Categorizer {
	static Categorizer singulete=null;

	Lemmatizer lemmatizer;
	Map<String, List<String>> taxonomyDict;

	public Categorizer() throws IOException {
		//loadTaxonomyDict();
		lemmatizer = Lemmatizer.getInstance();
	}

	public static Categorizer getInstance() throws IOException {
		if (singulete == null) {
			singulete = new Categorizer();
		}
		return singulete;
	}

	private void loadTaxonomyDict() throws IOException {
		Scanner taxFile = new Scanner(new GZIPInputStream(new FileInputStream(new File("resources/taxonomy_dict.gz"))));
		taxonomyDict = new HashMap<String, List<String>>();
		System.out.println("Loading taxonomy dict");
		while (taxFile.hasNextLine()) {
			String line = taxFile.nextLine();
			String[] entry = line.split("\t");
			
			if (!taxonomyDict.containsKey(entry[0]))
					taxonomyDict.put(entry[0], new ArrayList());
			
			taxonomyDict.get(entry[0]).add(entry[0]);
		}
		
		System.err.println("Taxonomy dict loaded.");
	}

	private static String getWordWithIndex(String sentence, int argRootIndex) {
		int index = 0;
		PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<CoreLabel>(new StringReader(sentence),
				new CoreLabelTokenFactory(), "");
		

		for (CoreLabel label : ptbt.tokenize()) {
			++index;

			if (index == argRootIndex) {
				
				return label.toString();
			}
		}
		return null;
	}

	public List<String> getCategory(String sentence, String[] argData) {
		String arg = argData[0];
		List<String> categories = new ArrayList();

		/**
		 * TODO: USE BLEU SCORE
		 */
		
		/*
		for (String key : this.taxonomyDict.keySet()) {
			if (key.contains(arg)) {
				categories.addAll(this.taxonomyDict.get(key));
			}
		}*/

		if (categories.isEmpty()) {// no category found
			System.out.println("No category found; using lemmatization on root word with index " + argData[1]);
			String argRoot = getWordWithIndex(sentence,Integer.valueOf(argData[1]));
			
			categories.add(lemmatizer.getLemma(argRoot));
			
			
		} else {
			System.err.println("Categories found!.");
		}
		return categories;
	}
}
