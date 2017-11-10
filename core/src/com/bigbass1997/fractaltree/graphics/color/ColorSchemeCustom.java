package com.bigbass1997.fractaltree.graphics.color;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

/**
 * A custom defined ColorScheme.
 */
public class ColorSchemeCustom implements ColorScheme {
	
	/**
	 * The "spread" of the colors, how many levels should the colors be spread out through.
	 */
	private int steps;
	
	/**
	 * List of colors in 0xRRGGBBAA format
	 */
	private int[] colors;
	
	public ColorSchemeCustom(int steps, int... colors){
		this.steps = steps;
		this.colors = colors;
	}
	
	/**
	 * Cycles through each segment, assigning colors to each corner of the segment to create a gradient effect.
	 */
	@Override
	public void invoke(ArrayList<Segment> segments) {
		int[] temp = GradientUtil.gradientColors(steps, colors);
		int[] reorderedTemp = new int[temp.length];
		
		for(int i = 0; i < temp.length; i++){
			reorderedTemp[i] = temp[-i + (temp.length - 1)];
		}
		
		for(int i = 0; i < reorderedTemp.length - 1; i++){
			for(Segment seg : segments){
				if(seg.level == i){
					seg.colors = new int[]{reorderedTemp[i],reorderedTemp[i],reorderedTemp[i + 1],reorderedTemp[i + 1]};
				}
			}
		}
		
		for(Segment seg : segments){
			if(seg.level > reorderedTemp.length - 2){
				seg.colors = new int[]{reorderedTemp[reorderedTemp.length-1],reorderedTemp[reorderedTemp.length-1],reorderedTemp[reorderedTemp.length-1],reorderedTemp[reorderedTemp.length-1]};
			}
		}
	}
}
