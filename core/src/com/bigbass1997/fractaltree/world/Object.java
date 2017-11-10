package com.bigbass1997.fractaltree.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Object {
	
	protected Vector2 pos;
	public Vector2 dim;
	
	public int[] colors;
	
	public Object(){
		this(Vector2.Zero, Vector2.Zero);
	}
	
	public Object(float x, float y, float z){
		this(new Vector2(x, y), Vector2.Zero);
	}
	
	public Object(Vector2 pos){
		this(pos, Vector2.Zero);
	}
	
	public Object(float x, float y, float xlen, float ylen){
		this(new Vector2(x, y), new Vector2(xlen, ylen));
	}
	
	public Object(float x, float y, Vector2 size){
		this(new Vector2(x, y), size);
	}
	
	public Object(Vector2 pos, Vector2 size){
		this.pos = pos;
		this.dim = size;
		this.colors = new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF};
	}
	
	public void render(ShapeRenderer sr){
		
	}
	
	public void update(float delta){
		
	}

	public void translate(float x, float y){
		pos.add(x, y);
	}
	
	public void dispose(){
		
	}
}
