package com.bigbass1997.fractaltree.world;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bigbass1997.fractaltree.graphics.color.ColorScheme;
import com.bigbass1997.fractaltree.graphics.color.ColorSchemeCustom;

/**
 * Controlls a single tree and all of its generation parameters
 */
public class TreeController {
	
	private Tree tree;
	
	private Range<Integer> generationsRange;
	
	private Range<Float> initWidthRange;
	private Range<Float> initHeightRange;
	private Range<Float> widthMultiplierRange;
	private Range<Float> heightMultiplierRange;
	
	private ArrayList<Range<Float>> degreeChangesRanges;
	
	private ColorScheme colorScheme;
	
	/**
	 * Prevents regeneration spam
	 */
	private boolean regenReady = false;
	
	public TreeController(){
		generationsRange = new Range<Integer>(0, 0);
		
		initWidthRange = new Range<Float>(0f, 0f);
		initHeightRange = new Range<Float>(0f, 0f);
		widthMultiplierRange = new Range<Float>(0f, 0f);
		heightMultiplierRange = new Range<Float>(0f, 0f);

		degreeChangesRanges = new ArrayList<Range<Float>>();
		
		colorScheme = new ColorSchemeCustom(3, 0x663212FF, 0x00FF00FF);
	}
	
	public void setGenerationsRange(int min, int max){
		generationsRange.set(min, max);
	}
	
	public void setInitWidthRange(float min, float max){
		initWidthRange.set(min, max);
	}

	public void setInitHeightRange(float min, float max){
		initHeightRange.set(min, max);
	}

	public void setWidthMultiplierRange(float min, float max){
		widthMultiplierRange.set(min, max);
	}

	public void setHeightMultiplierRange(float min, float max){
		heightMultiplierRange.set(min, max);
	}
	
	public void setDegreeChangesRanges(float... list){
		degreeChangesRanges.clear();
		if(list.length % 2 == 0){
			for(int i = 0; i < list.length; i += 2){
				degreeChangesRanges.add(new Range<Float>(list[i], list[i + 1]));
			}
		}
	}
	
	public void setColorScheme(ColorScheme colorScheme){
		if(colorScheme != null){
			this.colorScheme = colorScheme;
		}
	}
	
	public void regenerate(){
		if(!canGenerateSafely()) return;
		tree = null;

		Random rand = new Random();
		int generations = (rand.nextInt(generationsRange.max - generationsRange.min + 1) + generationsRange.min);
		float[] degreeChanges = new float[degreeChangesRanges.size()];
		for(int j = 0; j < degreeChangesRanges.size(); j++){
			degreeChanges[j] = ((rand.nextFloat() * (degreeChangesRanges.get(j).max - degreeChangesRanges.get(j).min)) + degreeChangesRanges.get(j).min);
		}
		float initWidth = ((rand.nextFloat() * (initWidthRange.max - initWidthRange.min)) + initWidthRange.min);
		float initHeight = ((rand.nextFloat() * (initHeightRange.max - initHeightRange.min)) + initHeightRange.min);
		float widthMultiplier = ((rand.nextFloat() * (widthMultiplierRange.max - widthMultiplierRange.min)) + widthMultiplierRange.min);
		float heightMultiplier = ((rand.nextFloat() * (heightMultiplierRange.max - heightMultiplierRange.min)) + heightMultiplierRange.min);
		
		tree = new Tree(generations, degreeChanges, initWidth, initHeight, widthMultiplier, heightMultiplier, colorScheme);
	}
	
	public boolean canGenerateSafely(){
		if(generationsRange == null || degreeChangesRanges == null || initWidthRange == null || initHeightRange == null || widthMultiplierRange == null || heightMultiplierRange == null || colorScheme == null) return false;
		return true;
	}
	
	public void render(ImmediateModeRenderer20 render, ShapeRenderer sr){
		if(tree != null){
			tree.render(render, sr);
		}
	}
	
	public void update(float delta){
		if(tree != null){
			tree.update(delta);
		}
		
		//If space is pressed, attempt tree regeneration
		if(Gdx.input.isKeyPressed(Keys.SPACE) && regenReady){
			regenerate();
			regenReady = false;
		} else if(!Gdx.input.isKeyPressed(Keys.SPACE) && !regenReady){
			regenReady = true;
		}
	}
}
