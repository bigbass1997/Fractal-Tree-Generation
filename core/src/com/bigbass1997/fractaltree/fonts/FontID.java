package com.bigbass1997.fractaltree.fonts;

public class FontID {
	
	public String name;
	public int size;
	
	public FontID(String name, int size){
		this.name = name;
		this.size = size;
	}
	
	@Override
	public String toString(){
		return name.concat(String.valueOf(size));
	}
}
