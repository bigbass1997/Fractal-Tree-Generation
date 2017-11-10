package com.bigbass1997.fractaltree.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bigbass1997.fractaltree.world.Object;

public class Segment extends Object {
	
	public float degrees;
	public int level;
	
	public Segment(Vector2 pos, Vector2 dim, int[] colors, float degrees){
		this(pos, dim, colors, degrees, -1);
	}
	
	public Segment(Vector2 pos, Vector2 dim, int[] colors, float degrees, int level){
		this.pos = pos;
		this.dim = dim;
		this.colors = colors;
		this.degrees = degrees+90; // Makes 0 degrees point to the direction it should be
		this.level = level;
	}
	
	@Override
	public void render(ShapeRenderer sr){
		sr.identity();
		sr.translate(pos.x, pos.y, 0);
		sr.rotate(0, 0, 1, degrees);
		sr.rect(-(dim.x/2), -dim.y, dim.x, dim.y, new Color(colors[0]), new Color(colors[1]), new Color(colors[2]), new Color(colors[3]));
		sr.identity();
	}
	
	@Override
	public void update(float delta){
		Input input = Gdx.input;
		
		if(input.isKeyPressed(Keys.O)){ //Rotates Segment counter-clockwise
			degrees += 1;
		}
		if(input.isKeyPressed(Keys.P)){ //Rotates Segment clockwise
			degrees -= 1;
		}
	}
	
	public Segment copy(){
		return new Segment(pos, dim, colors, degrees, level);
	}
}
