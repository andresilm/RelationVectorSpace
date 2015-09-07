package common;

import java.util.HashMap;
import java.util.List;



@SuppressWarnings("serial")
public class CountVector extends HashMap<String, Integer> {

	public float mean() {
		return 1.0f;
	}

	public float euclideanDistance(CountVector vec) {
		float result = 0f;
		System.err.println("euclidean distance not implemented yet!");
		return result;
	}

	public CountVector getNormalizedVector() {
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
	
	/*
	 * Creates a vector from count vector 
	 */
	
	public float[] createVector(int dimension) {
		float[] vector = new float[dimension];
		for (int i=0;i<dimension;++i) {
			String key = String.valueOf(i);
			vector[i] = ((this.containsKey(key)) ? (float)this.get(key) : 0f);
		}
		return vector;
	}

}