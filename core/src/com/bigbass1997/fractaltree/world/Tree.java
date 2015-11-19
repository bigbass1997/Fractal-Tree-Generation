package com.bigbass1997.fractaltree.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Tree {
	
	public ArrayList<Segment> segments;
	public int totalBranchLength, splits;
	
	/**
	 * <p>Creates a new Fractal Tree.</p>
	 * 
	 * @param totalBranchLength max number of segments in a line or branch of the tree
	 * @param splits number of new segments for each previous segment
	 */
	public Tree(int totalBranchLength, int splits){
		segments = new ArrayList<Segment>();
		this.totalBranchLength = totalBranchLength;
		this.splits = splits;
		
		generate();
	}
	
	private void generate(){
		float middle = (Gdx.graphics.getWidth() / 2) - 20;
		segments.add(new Segment(new Vector2(middle, 20), new Vector2(5, 100), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 0));
		segments.add(new Segment(new Vector2(middle + (MathUtils.cosDeg(90) * 5), 20 + (MathUtils.sinDeg(90) * 100)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, -45));
		segments.add(new Segment(new Vector2(middle + (MathUtils.cosDeg(90) * 5), 20 + (MathUtils.sinDeg(90) * 100)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 45));
		
		segments.add(new Segment(new Vector2(middle, 95 + (MathUtils.sinDeg(90) * 75)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, -45));
		//segments.add(new Segment(new Vector2(middle, 20 + 100), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 45));
	}
	
	public void render(ImmediateModeRenderer20 render, ShapeRenderer sr){
		for(Segment segment : segments){
			segment.render(sr);
		}
	}
	
	public void update(float delta){
		for(Segment segment : segments) segment.update(delta);
	}
}
