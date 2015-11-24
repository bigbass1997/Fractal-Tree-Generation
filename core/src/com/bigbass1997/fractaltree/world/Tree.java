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
	public float degreeChange;
	
	/**
	 * <p>Creates a new Fractal Tree.</p>
	 * 
	 * @param totalBranchLength max number of segments in a line or branch of the tree
	 * @param splits number of new segments for each previous segment
	 */
	public Tree(int totalBranchLength, int splits, float degreeChange){
		segments = new ArrayList<Segment>();
		this.totalBranchLength = totalBranchLength;
		this.splits = splits;
		this.degreeChange = degreeChange;
		
		float middle = (Gdx.graphics.getWidth() / 2) - 20;
		
		segments.add(new Segment(new Vector2(middle, 20), new Vector2(5, 100), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 90, totalBranchLength));
		
		generate(totalBranchLength);
	}
	
	private void generate(int len){
		
		if(len > 0){
			for(int i = 0; i < segments.size(); i++){
				Segment seg = segments.get(i);

				if(seg.level == len){
					Vector2 pos = new Vector2(seg.pos.x + (MathUtils.cosDeg(seg.degrees - 90) * seg.size.y), seg.pos.y + (MathUtils.sinDeg(seg.degrees - 90) * seg.size.y));
					Vector2 size = new Vector2(seg.size.x * 0.9f, seg.size.y * 0.75f);
					
					System.out.println((-len + totalBranchLength));
					segments.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, seg.degrees + degreeChange - 90, len - 1));
					segments.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, seg.degrees - degreeChange - 90, len - 1));
				}
			}
			generate(len - 1);
		}
		
		/*float middle = (Gdx.graphics.getWidth() / 2) - 20;
		
		ArrayList<Segment> tmpSegs = new ArrayList<Segment>();
		for(Segment seg : segments){
			
			//Create new segments here for any segments that already exist.
			//Each segment will use 75% of length from the previous segment.
			//A temp ArrayList of segments will have to be used to handle each new segment.
			//Use a NEW for each loop in order to properly append the real segments list.
			
			Vector2 pos = new Vector2(seg.pos.x + (MathUtils.cosDeg(90) * seg.size.x), seg.pos.y + (MathUtils.sinDeg(90) * (seg.size.y * 0.75f)));
			Vector2 size = new Vector2(5, seg.size.y * 0.75f);
			
			System.out.println(seg.pos + " | " + pos);
			
			tmpSegs.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, -45-180));
			tmpSegs.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 45-180));
		}
		for(Segment seg : tmpSegs){
			segments.add(seg.copy());
		}
		tmpSegs.clear();
		totalBranchLength--;
		if(totalBranchLength > 0) generate();
		
		System.out.println(segments.size());
		*/
		//end
		/*
		segments.add(new Segment(new Vector2(middle, 20), new Vector2(5, 100), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 0));
		segments.add(new Segment(new Vector2(middle + (MathUtils.cosDeg(90) * 5), 20 + (MathUtils.sinDeg(90) * 100)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, -45));
		segments.add(new Segment(new Vector2(middle + (MathUtils.cosDeg(90) * 5), 20 + (MathUtils.sinDeg(90) * 100)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 45));
		
		segments.add(new Segment(new Vector2(middle, 95 + (MathUtils.sinDeg(90) * 75)), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, -45));
		*///segments.add(new Segment(new Vector2(middle, 20 + 100), new Vector2(5, 75), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 45));
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
