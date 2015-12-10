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
	public float initHeight, initWidth, heightMultiplier, widthMultiplier;
	public float[] degreeChanges;
	
	public ColorScheme colorScheme;

	public Tree(int generations, int splits, float[] degreeChanges, float initWidth, float initHeight, float heightMultiplier, float widthMultiplier){
		this(generations, degreeChanges, initWidth, initHeight, heightMultiplier, widthMultiplier, new DefaultColorScheme());
	}
	
	/**
	 * <p>Creates a new Fractal Tree.</p>
	 * 
	 * @param generations number of generations per branch in the tree
	 * @param degreeChanges determines how many new branches from previous and what angle they are created at
	 * @param initHeight initial branch length/height
	 * @param initWidth initial branch width/thickness
	 * @param heightMultiplier how much smaller the height of the next generation of branches should be in decimal percentage 0.01 = 1% of previous
	 * @param widthMultiplier how much smaller the width of the next generation of branches should be in decimal percentage 0.01 = 1% of previous
	 */
	public Tree(int generations, float[] degreeChanges, float initWidth, float initHeight, float heightMultiplier, float widthMultiplier, ColorScheme colorScheme){
		segments = new ArrayList<Segment>();
		this.generations = generations;
		this.degreeChanges = degreeChanges;
		this.initWidth = initWidth;
		this.initHeight = initHeight;
		this.heightMultiplier = heightMultiplier;
		this.widthMultiplier = widthMultiplier;
		this.colorScheme = colorScheme;
		
		float middle = (Gdx.graphics.getWidth() / 2) - 20;
		
		segments.add(new Segment(new Vector2(middle, 80), new Vector2(initWidth, initHeight), new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, 90, generations));
		
		generate(generations);
		colorScheme.invoke(segments);
	}
	
	private void generate(int len){
		if(len > 0){
			for(int i = 0; i < segments.size(); i++){
				Segment seg = segments.get(i);

				if(seg.level == len){
					Vector2 pos = new Vector2(seg.pos.x + (MathUtils.cosDeg(seg.degrees - 90) * seg.size.y), seg.pos.y + (MathUtils.sinDeg(seg.degrees - 90) * seg.size.y));
					Vector2 size = new Vector2(seg.size.x * widthMultiplier, seg.size.y * heightMultiplier);
					
					for(int j = 0; j < degreeChanges.length; j++){
						segments.add(new Segment(pos, size, new int[]{0x000000FF,0x000000FF,0x000000FF,0x000000FF}, seg.degrees + degreeChanges[j] - 180, len - 1));
					}
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
