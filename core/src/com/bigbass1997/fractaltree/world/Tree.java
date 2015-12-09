package com.bigbass1997.fractaltree.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bigbass1997.fractaltree.graphics.ColorScheme;
import com.bigbass1997.fractaltree.graphics.DefaultColorScheme;

public class Tree {
	
	public ArrayList<Segment> segments;
	public int generations, splits;
	public float degreeChangeLeft, degreeChangeRight, initLength, initWidth, lengthMultiplier, widthMultiplier;
	
	private ColorScheme colorScheme;
	

	public Tree(int generations, int splits, float degreeChangeLeft, float degreeChangeRight, float initWidth, float initLength, float lengthMultiplier, float widthMultiplier){
		this(generations, splits, degreeChangeLeft, degreeChangeRight, initWidth, initLength, lengthMultiplier, widthMultiplier, new DefaultColorScheme());
	}
	
	/**
	 * <p>Creates a new Fractal Tree.</p>
	 * 
	 * @param generations number of generations per branch in the tree
	 * @param splits number of new branches from each existing branch per generation
	 * @param degreeChangeLeft degrees for each successive branch on the left of previous
	 * @param degreeChangeRight degrees for each successive branch on the right of previous
	 * @param initLength initial branch length/height
	 * @param initWidth initial branch width/thickness
	 * @param lengthMultiplier how much smaller the length of the next generation of branches should be in decimal percentage 0.01 = 1% of previous
	 * @param widthMultiplier how much smaller the width of the next generation of branches should be in decimal percentage 0.01 = 1% of previous
	 */
	public Tree(int generations, int splits, float degreeChangeLeft, float degreeChangeRight, float initWidth, float initLength, float lengthMultiplier, float widthMultiplier, ColorScheme colorScheme){
		segments = new ArrayList<Segment>();
		this.generations = generations;
		this.splits = splits; // Unused at this time
		this.degreeChangeLeft = degreeChangeLeft;
		this.degreeChangeRight = degreeChangeRight;
		this.initWidth = initWidth;
		this.initLength = initLength;
		this.lengthMultiplier = lengthMultiplier;
		this.widthMultiplier = widthMultiplier;
		this.colorScheme = colorScheme;
		
		float middle = (Gdx.graphics.getWidth() / 2) - 20;
		
		segments.add(new Segment(new Vector2(middle, 80), new Vector2(initWidth, initLength), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 90, generations));
		
		generate(generations);
		colorScheme.invoke(segments);
	}
	
	private void generate(int len){
		if(len > 0){
			for(int i = 0; i < segments.size(); i++){
				Segment seg = segments.get(i);

				if(seg.level == len){
					Vector2 pos = new Vector2(seg.pos.x + (MathUtils.cosDeg(seg.degrees - 90) * seg.size.y), seg.pos.y + (MathUtils.sinDeg(seg.degrees - 90) * seg.size.y));
					Vector2 size = new Vector2(seg.size.x * widthMultiplier, seg.size.y * lengthMultiplier);
					
					segments.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, seg.degrees + degreeChangeLeft - 90, len - 1));
					segments.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, seg.degrees - degreeChangeRight - 90, len - 1));
				}
			}
			generate(len - 1);
		}
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
