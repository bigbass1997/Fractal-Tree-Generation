package com.bigbass1997.fractaltree.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
		if(!segments.isEmpty()) segments.clear();
		int level = 0;
		
		Vector2 dim = new Vector2(5f, 20f);
		
		segments.add(new Segment(new Vector3(0, 0, 0), dim, 0x000000FF, 0, level));
		
		while(level < totalBranchLength){
			ArrayList<Segment> tmpList = new ArrayList<Segment>();
			
			for(Segment segment : segments){
				if(segment.level == level){
					Segment newSegment = new Segment(Vector3.Zero, dim, 0x000000FF, 45f*(level + 1), level + 1);
					newSegment.setPos(segment.getPos().add(0, segment.size.y, 0));
					//newSegment.rotate(45f*(level + 1));
					tmpList.add(newSegment);
				}
			}
			
			segments.addAll(tmpList);
			level++;
		}
	}
	
	public void render(ModelBatch modelBatch, Environment environment){
		for(Segment segment : segments){
			segment.render();
			modelBatch.render(segment.modelInstance, environment);
		}
	}
	
	public void update(float delta){
		for(Segment segment : segments) segment.update(delta);
	}
}
