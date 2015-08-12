package main;

import java.util.List;

public class LineProcessing {
	@SuppressWarnings("static-access")
	public static void processLine(int lineCounter, String line, SharedVectorsCollection relationsVectors,
			List<String> relationsToBuildFor) throws InterruptedException {

		final int sentCol = 0;
		final int dataCol = 2;
		final int relCol = 0;
		final int predCol = 0;
		final int arg1Col = 1;
		final int arg2Col = 2;

		final int normPredCol = 2;
		final int argRootWordIndexCol = 1;

		boolean selectedRelationsOnly = relationsToBuildFor != null;

		String[] lineSplit = line.split("\\|");
		String sentence = lineSplit[sentCol];
		String[] extrRels = lineSplit[dataCol].split("\\;");

		for (String relData : extrRels) {
			if (!relData.equals("")) {
				String[] relCols = relData.split("\\#")[relCol].split("\\,");

				String pred = relCols[predCol].split("\\:")[normPredCol];

				int arg1RootIndex = Integer.valueOf(relCols[arg1Col].split("\\:")[argRootWordIndexCol]);
				int arg2RootIndex = Integer.valueOf(relCols[arg2Col].split("\\:")[argRootWordIndexCol]);

				String arg1Root = getWordWithIndex(sentence, arg1RootIndex);
				String arg2Root = getWordWithIndex(sentence, arg2RootIndex);

				if (!arg1Root.equals(" ") && !arg2Root.equals(" ")) {

					relationsVectors.increaseArgumentCount(pred, 0, arg1Root);
					relationsVectors.increaseArgumentCount(pred, 1, arg2Root);
				} else {
					System.err.println("Skipping line, at least one of the arguments were empty.");
				}
			}
		}
		// System.err.println("Processed line nr "+ lineCounter+" on thread" +
		// Thread.currentThread().getName());

	}

	private static String getWordWithIndex(String sentence, int arg1RootIndex) {
		int start = 0;

		char[] sent = sentence.toCharArray();
		for (int i = 0; i < sentence.length(); ++i) {
			if (sent[i] == ' ' || sent[i] == ',' || sent[i] == '"' || sent[i] == ';' || sent[i] == '-' || sent[i] == '('
					|| sent[i] == ')' || sent[i] == '{' || sent[i] == '}' || sent[i] == '[' || sent[i] == ']')
				++start;
			if (start+1 == arg1RootIndex) //stanford parser indices start with 1
				break;
		}
		int end = start;
		for (int i = start; i < sentence.length(); ++i) {
			if (sent[i] == ' ' || sent[i] == ',' || sent[i] == '"' || sent[i] == ';' || sent[i] == '-' || sent[i] == '('
					|| sent[i] == ')' || sent[i] == '{' || sent[i] == '}' || sent[i] == '[' || sent[i] == ']') {
				end = i - 1;
				break;
			}
		}

		// TODO Auto-generated method stub
		return sentence.substring(start, end);
	}

}
