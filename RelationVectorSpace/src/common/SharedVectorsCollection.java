package common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SharedVectorsCollection {
	Map<String, RelationVector> relationsVectors;
	private Map<String,Integer> arg1Features;
	private Map<String,Integer> arg2Features;
	

	public SharedVectorsCollection() {
		relationsVectors = new HashMap<String, RelationVector>();
		
		setArg1Features(new HashMap<String,Integer>());
		setArg2Features(new HashMap<String,Integer>());
	}

	public synchronized void increaseArgumentCount(String relName, int argIndex, String category) {
		int index=-1;
		if (argIndex == 0) {
			if (!getArg1Features().containsKey(category)) {
				index = getArg1Features().size();
				getArg1Features().put(category, index);
			}
				
		}
			
		else if (argIndex == 1) {
			index = getArg2Features().size();
			if (!getArg2Features().containsKey(category)) {
				getArg2Features().put(category, index);
			}
		}
		
		if (!relationsVectors.containsKey(relName)) {

			RelationVector rvector = new RelationVector();

			relationsVectors.put(relName, rvector);
		}
		
		String catKey = String.valueOf(index);

		relationsVectors.get(relName).addOccurrenceToArgumentVector(argIndex, catKey);
		
		
			

	}

	public Map<String, RelationVector> getVectors() {
		return relationsVectors;
	}

	public Map<String,Integer> getArg1Features() {
		return arg1Features;
	}

	private void setArg1Features(Map<String,Integer> arg1Features) {
		this.arg1Features = arg1Features;
	}

	public Map<String,Integer> getArg2Features() {
		return arg2Features;
	}

	private void setArg2Features(Map<String,Integer> arg2Features) {
		this.arg2Features = arg2Features;
	}

	public int dimensionOfVectorArgument1() {
		// TODO Auto-generated method stub
		return arg1Features.size();
	}

	public int dimensionOfVectorArgument2() {
		// TODO Auto-generated method stub
		return arg2Features.size();
	}


	
	
}

