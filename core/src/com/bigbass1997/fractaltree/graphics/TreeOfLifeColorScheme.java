package com.bigbass1997.fractaltree.graphics;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

public class TreeOfLifeColorScheme implements ColorScheme {
	
	/**
	 * Colors found from <a href="http://www.colourlovers.com/palette/97779/Tree_of_Knowledge">http://www.colourlovers.com/palette/97779/Tree_of_Knowledge</a>
	 */
	private int TREETRUNK = 0x4D3204FF,
				ZIPPI = 0x0A6906FF,
				SNAKE = 0x27BE22FF,
				LIGHTLEAF = 0x219E1CFF,
				FORBIDFRUIT = 0xC7091FFF;
	
	@Override
	public void invoke(ArrayList<Segment> segments){
		int[] temp = GradientUtil.gradientColors(4, new int[]{TREETRUNK,ZIPPI,SNAKE,LIGHTLEAF,FORBIDFRUIT});
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
