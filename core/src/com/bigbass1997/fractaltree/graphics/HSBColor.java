package com.bigbass1997.fractaltree.graphics;

public class HSBColor {
	
	public float h, s, b;
	
	public HSBColor(float h, float s, float b){
		this.h = h;
		this.s = s;
		this.b = b;
	}
	
	@Override
	public String toString(){
		return "HSBCol.h=" + h + ".s=" + s + ".b=" + b;
	}
}
