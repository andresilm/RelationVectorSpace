/*
 * Change the implementation of this class to try other ways to compute the arguments, e.g. occurrences
 * of pairs of (subj,obj)
 */

package main;

public class RelationVector {
	ArgVector[] vectors;
	public final int ARG1 = 0;
	public final int ARG2 = 1;
	
	RelationVector() {
		vectors = new ArgVector[2];
		vectors[ARG1] = new ArgVector();
		vectors[ARG2] = new ArgVector();
	}
	
	public float similarity(RelationVector rvec) {
		return 0f;
	}
	
	public void addOccurrenceToArgumentVector(int argIndex, String argKey)  {
		vectors[argIndex].increaseCountOnKey(argKey);
	}
	
}
