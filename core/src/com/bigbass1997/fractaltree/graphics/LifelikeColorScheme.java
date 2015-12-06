package com.bigbass1997.fractaltree.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.bigbass1997.fractaltree.world.Segment;

public class LifelikeColorScheme implements ColorScheme {
	
	private int BROWN = 0x663212FF, GREEN = 0x27BE22FF;
	
	@Override
	public void invoke(ArrayList<Segment> segments) {
		
		int[] temp = gradientColors(BROWN, GREEN, 3);
		
		for(Segment seg : segments){
			switch(seg.level){
			case 0:
				seg.colors = new int[]{temp[3],temp[3],temp[2],temp[2]};
				break;
			case 1:
				seg.colors = new int[]{temp[2],temp[2],temp[1],temp[1]};
				break;
			case 2:
				seg.colors = new int[]{temp[1],temp[1],temp[0],temp[0]};
				break;
			default:
				seg.colors = new int[]{temp[0],temp[0],temp[0],temp[0]};
				break;
			}
		}
		
		System.out.println("start | r=" + (new Color(BROWN)).r + " | g=" + (new Color(BROWN)).g + " | b=" + (new Color(BROWN)).b);
		for(int i = 0; i < temp.length; i++){
			System.out.println(i + " | r=" + (new Color(temp[i])).r + " | g=" + (new Color(temp[i])).g + " | b=" + (new Color(temp[i])).b);
		}
		System.out.println("end | r=" + (new Color(GREEN)).r + " | g=" + (new Color(GREEN)).g + " | b=" + (new Color(GREEN)).b);
	}
	
	public int[] gradientColors(int begin, int end, int steps){
		int[] temp = new int[steps + 1];
		temp[0] = begin;
		
		float percent = 1f / (steps);
		System.out.println("percent=" + percent);
		
		Color beginCol = new Color(begin);
		Color endCol = new Color(end);
		for(int i = 1; i < (steps+1); i++){
			float r = (beginCol.r * (percent * i)) + (endCol.r * (1 - (percent * i)));
			float g = (beginCol.g * (percent * i)) + (endCol.g * (1 - (percent * i)));
			float b = (beginCol.b * (percent * i)) + (endCol.b * (1 - (percent * i)));
			float a = (beginCol.a * (percent * i)) + (endCol.a * (1 - (percent * i)));
			
			temp[i] = Color.rgba8888(r, g, b, a);
		}
		
		return temp;
	}
}
