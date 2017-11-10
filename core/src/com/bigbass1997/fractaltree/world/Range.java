package com.bigbass1997.fractaltree.world;

public class Range<T extends Number> {
	
	public T min, max;
	
	public Range(T min, T max){
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Helper method to quickly redefine both values, without setting them each manually.
	 * 
	 * @param min
	 * @param max
	 */
	public void set(T min, T max){
		this.min = min;
		this.max = max;
	}
}