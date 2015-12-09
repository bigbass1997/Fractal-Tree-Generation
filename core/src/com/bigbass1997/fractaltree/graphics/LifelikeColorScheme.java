package com.bigbass1997.fractaltree.graphics;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

public class LifelikeColorScheme implements ColorScheme {
	
	private int BROWN = 0x663212FF, GREEN = 0x00FF00FF;
	
	@Override
	public void invoke(ArrayList<Segment> segments){
		int[] temp = GradientUtil.gradientColors(2, new int[]{BROWN, GREEN});
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
