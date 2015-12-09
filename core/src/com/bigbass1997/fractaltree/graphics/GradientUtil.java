package com.bigbass1997.fractaltree.graphics;

import com.badlogic.gdx.graphics.Color;

public class GradientUtil {

	public static int[] gradientColors(int steps, int[] colors){
		int[] temp = new int[steps + 1];
		int parts = colors.length - 1;

		int gradientIndex = 0;
		float partSteps = (float) Math.floor(steps / parts);
		float remainder = steps - (partSteps * parts);
		
		for(int i = 0; i < parts; i++){
			Color tempc1 = new Color(colors[i]);
			float[] c1comps = java.awt.Color.RGBtoHSB((int) (tempc1.r * 255), (int) (tempc1.g * 255), (int) (tempc1.b * 255), null);
			HSBColor c1 = new HSBColor(c1comps[0], c1comps[1], c1comps[2]);
			
			Color tempc2 = new Color(colors[i + 1]);
			float[] c2comps = java.awt.Color.RGBtoHSB((int) (tempc2.r * 255), (int) (tempc2.g * 255), (int) (tempc2.b * 255), null);
			HSBColor c2 = new HSBColor(c2comps[0], c2comps[1], c2comps[2]);
			
			float distCCW = (c1.h >= c2.h) ? c1.h - c2.h : 1 + c1.h - c2.h;
			float distCW = (c1.h >= c2.h) ? 1 + c2.h - c1.h : c2.h - c1.h;
			
			if(i == parts-1) partSteps += remainder;
			
			for(int step = 0; step < partSteps; step++){
				float p = step/partSteps;
				
				float h = (distCW <= distCCW) ? c1.h + (distCW * p) : c1.h - (distCCW * p);
				if(h < 0) h = 1 + h;
				if(h > 1) h = h - 1;
				float s = (1 - p) * c1.s + p * c2.s;
				float b = (1 - p) * c1.b + p * c2.b;
				
				int tempFinalColInt = java.awt.Color.HSBtoRGB(h, s, b);
				java.awt.Color awtColor = new java.awt.Color(tempFinalColInt);
				Color tempFinalCol = new Color(awtColor.getRed()/255f, awtColor.getGreen()/255f, awtColor.getBlue()/255f, awtColor.getAlpha()/255f);
				temp[gradientIndex] = Color.rgba8888(tempFinalCol);
				gradientIndex++;
			}
		}
		temp[temp.length - 1] = colors[colors.length - 1];
		return temp;
	}
}
