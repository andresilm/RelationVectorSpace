/*
 * Change the implementation of this class to try other ways to compute the arguments, e.g. occurrences
 * of pairs of (subj,obj)
 */

package main;

import java.util.HashMap;

public class RelationVector {
	public final int ARG1 = 0;
	public final int ARG2 = 1;
	boolean normalized = false;

	@SuppressWarnings("serial")
	class Vector extends HashMap<String, Integer> {

		public float mean() {
			return 1.0f;
		}

		public float euclideanDistance(Vector vec) {
			float result = 0f;
			System.err.println("euclidean distance not implemented yet!");
			return result;
		}

		public Vector getNormalizedVector() {
			System.err.println("getNormalizedVector not implemented yet!");
			return null;

		}

		@Override
		public String toString() {
			String ret = "";
			for (String key : this.keySet()) {
				ret += key + ":" + this.get(key) + ",";
			}
			return ret;
		}

		void increaseCountOnKey(String key) {
			if (!this.containsKey(key)) {
				this.put(key, 0);
			}

			this.put(key, this.get(key) + 1);
		}

	}

	private Vector[] vectors;

	RelationVector() {
		vectors = new Vector[2];
		vectors[ARG1] = new Vector();
		vectors[ARG2] = new Vector();
	}

	public float similarity(RelationVector rvec) {
		return (vectors[ARG1].euclideanDistance(rvec.vectors[ARG1])
				+ vectors[ARG2].euclideanDistance(rvec.vectors[ARG2])) / 2.0f;
	}

	public void addOccurrenceToArgumentVector(int argIndex, String argKey) {
		vectors[argIndex].increaseCountOnKey(argKey);
	}

	@Override
	public String toString() {
		return vectors[ARG1].toString() + "|0,0|" + vectors[ARG2].toString();
	}

	public void normalize() {
		if (!normalized) {
			vectors[ARG1] = vectors[ARG1].getNormalizedVector();
			vectors[ARG2] = vectors[ARG2].getNormalizedVector();
			normalized = true;
		}

	}

	public boolean isNormalized() {
		return normalized;
	}
}
