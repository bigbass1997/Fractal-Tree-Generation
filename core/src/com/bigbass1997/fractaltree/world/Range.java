package com.bigbass1997.fractaltree.world;

public class Range<T extends Number> {
	
	public T min, max;
	
	public Range(T min, T max){
		this.min = min;
		this.max = max;
	}
}