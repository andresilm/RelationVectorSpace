package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.Semaphore;

public class SharedRelationVectors {
	Map<String,Map<String,Integer>[] > relationsVectors;
//	private final Semaphore available  = new Semaphore(1);
	
	public final int ARG1 = 0;
	public final int ARG2 = 1;
	
	SharedRelationVectors() {
		relationsVectors = new HashMap<String, Map<String, Integer>[]>();
		
	}
	
	public synchronized void addOccurrenceToArgumentVector(String key, int argIndex, String argKey)  {
		//available.acquire();
		
		if (!relationsVectors.containsKey(key)) {
			Map<String,Integer> arg1 = new HashMap<String,Integer>();
			Map<String,Integer> arg2 = new HashMap<String,Integer>();
			Map<String,Integer>[] vectors = new HashMap[2]; vectors[ARG1] = arg1;vectors[ARG2] = arg2;
			
			relationsVectors.put(key,vectors);
		}
		
		if (!relationsVectors.get(key)[argIndex].containsKey(argKey)) {
			relationsVectors.get(key)[argIndex].put(argKey, 0);
		}
		
		relationsVectors.get(key)[argIndex].put(argKey,relationsVectors.get(key)[argIndex].get(argKey)+1);
		
		//available.release();
	}
	
	public Map<String,Map<String,Integer>[] > getVectors() {
		return relationsVectors;
	}
}
