package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

public class Categorizer {
	static Categorizer singulete = null;
	static String[] personalPronouns = {"I","you","he","she","it","we","they","him","her","them"};

	Lemmatizer lemmatizer;
	Map<String, List<String>> taxonomyDict;

	public Categorizer() throws IOException {
		loadTaxonomyDict();
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
			line = line.toLowerCase();
			String[] entry = line.split("\t");

			if (!taxonomyDict.containsKey(entry[0]))
				taxonomyDict.put(entry[0], new ArrayList());

			taxonomyDict.get(entry[0]).add(entry[0]);
		}
		taxFile.close();

		System.out.println("Taxonomy dict loaded.");
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
		String arg = argData[0].toLowerCase().replace("\\ ", "");
		List<String> categories = null;
		if (!isPersonalPronoun(arg)) {
			
			categories = new ArrayList();

			/**
			 * TODO: USE BLEU SCORE
			 */
			arg = arg.replace("\\_", "\\ ");

			String key = getKeyWithHighestOverlap(arg);
			if (key != null) {
				System.out.println("Categories found!.");
				System.err.println(arg + " --> " + key);
				categories.addAll(this.taxonomyDict.get(key));
			} else {// no category found
				System.out.println("No category found; using lemmatization on root word with index " + argData[1]);
				String argRoot = getWordWithIndex(sentence, Integer.valueOf(argData[1]));
				String lemma = lemmatizer.getLemma(argRoot);
				categories.add(lemma);

			}
		}

		return categories;
	}

	private boolean isPersonalPronoun(String arg) {
		
		return Arrays.asList(personalPronouns).contains(arg);
	}

	private String getKeyWithHighestOverlap(String arg) {
		int maxOverlap = 0;
		String highestOverlapKey = null;

		for (String key : taxonomyDict.keySet()) {
			int overlap = 0;
			String[] keyWordsS = key.split("\\ ");
			String[] argWordsS = arg.split("\\ ");

			List<String> keyWords = Arrays.asList(keyWordsS);
			List<String> argWords = Arrays.asList(argWordsS);

			for (String argWord : argWords) {
				if (keyWords.contains(argWord)) {
					++overlap;
				}

			}
			if (overlap > maxOverlap) {
				maxOverlap = overlap;
				highestOverlapKey = key;
			}
		}

		return highestOverlapKey;

	}
}
