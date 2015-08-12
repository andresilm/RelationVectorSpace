/*
 * Change the implementation of this class to try other ways to compute the arguments, e.g. occurrences
 * of pairs of (subj,obj)
 */

package main;

import java.util.HashMap;



public class RelationVector {
	public final int ARG1 = 0;
	public final int ARG2 = 1;

	class Vector extends HashMap<String, Integer> {
		
		public float mean() {
			return 1.0f;
		}
		
		public float cosineDistance(Vector vec) {
			return 1.0f;
		}
		
		@Override
		public String toString() {
			return "";
		}
		
		void increaseCountOnKey(String key) {
			if (!this.containsKey(key)) {
				this.put(key, 0);
			}
			
			this.put(key,this.get(key)+1);
		}	
		
	}

	Vector[] vectors;
	
	
	RelationVector() {
		vectors = new Vector[2];
		vectors[ARG1] = new Vector();
		vectors[ARG2] = new Vector();
	}
	
	public float similarity(RelationVector rvec) {
		return 0f;
	}
	
	public void addOccurrenceToArgumentVector(int argIndex, String argKey)  {
		vectors[argIndex].increaseCountOnKey(argKey);
	}
	
}
