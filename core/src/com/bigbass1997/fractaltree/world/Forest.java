package com.bigbass1997.fractaltree.world;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bigbass1997.fractaltree.graphics.color.ColorScheme;
import com.bigbass1997.fractaltree.graphics.color.ColorSchemeDefault;
import com.bigbass1997.fractaltree.graphics.growth.GrowScheme;
import com.bigbass1997.fractaltree.graphics.growth.GrowSchemeDefault;

public class Forest {
	
	public ArrayList<Tree> trees;
	public Range<Integer> generationsRange, numOfTreesRange;
	public Range<Float> initWidthRange, initHeightRange, widthMultiplierRange, heightMultiplierRange;
	public ArrayList<Range<Float>> degreeChangesRanges;
	
	public ColorScheme colorScheme;
	public GrowScheme growScheme;
	
	public Forest(){
		this(null, null, null, null, null, null, null, new ColorSchemeDefault(), new GrowSchemeDefault());
	}
	
	public Forest(Range<Integer> generationsRange, ArrayList<Range<Float>> degreeChangesRanges, Range<Float> initWidthRange, Range<Float> initHeightRange, Range<Float> widthMultiplierRange, Range<Float> heightMultiplierRange, Range<Integer> numOfTreesRange, ColorScheme colorScheme, GrowScheme growScheme){
		this.generationsRange = generationsRange;
		this.degreeChangesRanges = degreeChangesRanges;
		this.initWidthRange = initWidthRange;
		this.initHeightRange = initHeightRange;
		this.widthMultiplierRange = widthMultiplierRange;
		this.heightMultiplierRange = heightMultiplierRange;
		this.numOfTreesRange = numOfTreesRange;
		this.colorScheme = colorScheme;
		this.growScheme = growScheme;
		
		trees = new ArrayList<Tree>();
	}
	
	public void regenerate(Range<Integer> generationsRange, ArrayList<Range<Float>> degreeChangesRanges, Range<Float> initWidthRange, Range<Float> initHeightRange, Range<Float> widthMultiplierRange, Range<Float> heightMultiplierRange, Range<Integer> numOfTreesRange, ColorScheme colorScheme, GrowScheme growScheme){
		this.generationsRange = generationsRange;
		this.degreeChangesRanges = degreeChangesRanges;
		this.initWidthRange = initWidthRange;
		this.initHeightRange = initHeightRange;
		this.widthMultiplierRange = widthMultiplierRange;
		this.heightMultiplierRange = heightMultiplierRange;
		this.numOfTreesRange = numOfTreesRange;
		this.colorScheme = colorScheme;
		this.growScheme = growScheme;
		
		regenerate();
	}
	
	public void regenerate(){
		if(!canForestGenerateSafely()) return;
		if(!trees.isEmpty()) trees.clear();

		Random rand = new Random();
		int numOfTrees = (rand.nextInt(numOfTreesRange.max - numOfTreesRange.min + 1) + numOfTreesRange.min);
		for(int i = 0; i < numOfTrees; i++){
			int generations = (rand.nextInt(generationsRange.max - generationsRange.min + 1) + generationsRange.min);
			float[] degreeChanges = new float[degreeChangesRanges.size()];
			for(int j = 0; j < degreeChangesRanges.size(); j++){
				degreeChanges[j] = ((rand.nextFloat() * (degreeChangesRanges.get(j).max - degreeChangesRanges.get(j).min)) + degreeChangesRanges.get(j).min);
			}
			float initWidth = ((rand.nextFloat() * (initWidthRange.max - initWidthRange.min)) + initWidthRange.min);
			float initHeight = ((rand.nextFloat() * (initHeightRange.max - initHeightRange.min)) + initHeightRange.min);
			float widthMultiplier = ((rand.nextFloat() * (widthMultiplierRange.max - widthMultiplierRange.min)) + widthMultiplierRange.min);
			float heightMultiplier = ((rand.nextFloat() * (heightMultiplierRange.max - heightMultiplierRange.min)) + heightMultiplierRange.min);
			
			trees.add(new Tree(generations, degreeChanges, initWidth, initHeight, widthMultiplier, heightMultiplier, colorScheme, growScheme));
		}
	}
	
	public boolean canForestGenerateSafely(){
		if(generationsRange == null || degreeChangesRanges == null || initWidthRange == null || initHeightRange == null || widthMultiplierRange == null || heightMultiplierRange == null || numOfTreesRange == null) return false;
		if(colorScheme == null || growScheme == null) return false;
		return true;
	}
	
	public void render(ImmediateModeRenderer20 render, ShapeRenderer sr){
		for(Tree tree : trees) tree.render(render, sr);
	}
	
	public void update(float delta){
		for(Tree tree : trees) tree.update(delta);
	}
}
