package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedRelationVectors {
	Map<String, RelationVector> relationsVectors;
	

	SharedRelationVectors() {
		relationsVectors = new HashMap<String, RelationVector>();

	}

	public synchronized void increaseArgumentCount(String key, int argIndex, String argKey) {

		if (!relationsVectors.containsKey(key)) {

			RelationVector rvector = new RelationVector();

			relationsVectors.put(key, rvector);
		}

		relationsVectors.get(key).addOccurrenceToArgumentVector(argIndex, argKey);

	}

	public Map<String, RelationVector> getVectors() {
		return relationsVectors;
	}
}
