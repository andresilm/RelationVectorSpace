package main;

import java.util.HashMap;

import java.util.Map;


public class ArgVector extends HashMap<String, Integer> {
	
	public float mean() {
		return 1.0f;
	}
	
	public float cosineDistance(ArgVector vec) {
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
