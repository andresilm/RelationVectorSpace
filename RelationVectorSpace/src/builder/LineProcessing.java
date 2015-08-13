package builder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import common.Categorizer;
import common.SharedVectorsCollection;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

public class LineProcessing {


	

	/*
	 * Python version Compatibility: process line of FramEx extraction in the
	 * old format.
	 */
	public static void processLineStub(int lineCounter, String line, SharedVectorsCollection relationsVectors,
			List<String> relationsToBuildFor) throws InterruptedException {
		relationsVectors.increaseArgumentCount("no_rel", 0, Thread.currentThread().getName());
		relationsVectors.increaseArgumentCount("no_rel", 1, Thread.currentThread().getName());
	}

	@SuppressWarnings("static-access")
	public static void processLine(int lineCounter, String line, SharedVectorsCollection relationsVectors,
			List<String> relationsToBuildFor) throws InterruptedException, IOException {
		
		Categorizer categorizer = Categorizer.getInstance();
		final int sentCol = 1;
		final int dataCol = 3;
		final int relCol = 0;
		final int predCol = 0;
		final int arg1Col = 1;
		final int arg2Col = 2;

		final int normPredCol = 2;
		final int argRootWordIndexCol = 1;

		boolean selectedRelationsOnly = relationsToBuildFor != null;

		String[] lineSplit = line.split("\\|");

		if (lineSplit.length < 4) {
			System.out.println("Skipping line, no relation data found.");
			
		} else {
			String sentence = lineSplit[sentCol];

			String[] extrRels = lineSplit[dataCol].split("\\;");

			for (String relData : extrRels) {
				if (!relData.equals("")) {
					String[] relCols = relData.split("\\#")[relCol].split("\\,");

					String pred = relCols[predCol].split("\\:")[normPredCol];

					if (!selectedRelationsOnly || relationsToBuildFor.contains(pred)) {

						String[] argData1 = relCols[arg1Col].split("\\:");
						String[] argData2 = relCols[arg2Col].split("\\:");
						
						/*System.out.println("Extracted data");
						System.out.println(argData1[0]);
						System.out.println(argData1[1]);
						System.out.println(argData2[0]);
						System.out.println(argData2[1]);
						System.out.println("[[END]Extracted data");
						*/

						List<String> arg1Categ = categorizer.getCategory(sentence, argData1);
						List<String> arg2Categ = categorizer.getCategory(sentence, argData2);

						if (arg1Categ != null && arg2Categ != null) {
							if (arg1Categ.isEmpty() || arg2Categ.isEmpty())
								System.err.println("WARNING: empty category. Size should be of size > 1, == 1, or null");
							
							for (String category : arg1Categ)
								relationsVectors.increaseArgumentCount(pred, 0, category);
							
							for (String category : arg2Categ)
								relationsVectors.increaseArgumentCount(pred, 1, category);
						} 
					}
				}
			}
			// System.err.println("Processed line nr "+ lineCounter+" on thread"
			// +
			// Thread.currentThread().getName());
		}
	}

	/*
	 * Process line of FramEx extraction in the new format
	 */


		/*
		 * int start = 0;
		 * 
		 * char[] sent = sentence.toCharArray(); for (int i = 0; i <
		 * sentence.length(); ++i) { if (sent[i] == ' ' || sent[i] == ',' ||
		 * sent[i] == '"' || sent[i] == ';' || sent[i] == '-' || sent[i] == '('
		 * || sent[i] == ')' || sent[i] == '{' || sent[i] == '}' || sent[i] ==
		 * '[' || sent[i] == ']') ++start; if (start + 1 == arg1RootIndex) //
		 * stanford parser indices start // with 1 break; } int end = start; for
		 * (int i = start; i < sentence.length(); ++i) { if (sent[i] == ' ' ||
		 * sent[i] == ',' || sent[i] == '"' || sent[i] == ';' || sent[i] == '-'
		 * || sent[i] == '(' || sent[i] == ')' || sent[i] == '{' || sent[i] ==
		 * '}' || sent[i] == '[' || sent[i] == ']') { end = i; break; } }
		 * 
		 * // TODO Auto-generated method stub return sentence.substring(start,
		 * end);
		 */
	

}
