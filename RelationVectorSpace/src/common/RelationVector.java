/*
 * Change the implementation of this class to try other ways to compute the arguments, e.g. occurrences
 * of pairs of (subj,obj)
 */

package common;

import java.util.HashMap;

public class RelationVector {
	public final int ARG1 = 0;
	public final int ARG2 = 1;
	boolean normalized = false;

	@SuppressWarnings("serial")
	

	private CountVector[] vectors;

	RelationVector() {
		vectors = new CountVector[2];
		vectors[ARG1] = new CountVector();
		vectors[ARG2] = new CountVector();
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
