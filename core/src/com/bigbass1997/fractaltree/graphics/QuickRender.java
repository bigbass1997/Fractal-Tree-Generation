package com.bigbass1997.fractaltree.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;

public class QuickRender {
	
	private Camera cam;
	private ImmediateModeRenderer20 rend;
	
	private boolean renderingLines = false;
	
	public QuickRender(Camera cam, ImmediateModeRenderer20 rend){
		this.cam = cam;
		this.rend = rend;
	}
	
	public void beginLines(){
		rend.begin(cam.combined, GL30.GL_LINES);
		renderingLines = true;
	}
	
	public void endLines(){
		rend.end();
		renderingLines = false;
	}
	
	public boolean line(float x1, float y1, float z1, int c1, float x2, float y2, float z2, int c2){
		if(!renderingLines) return false;
		
		try {
			rend.color(new Color(c1));
			rend.vertex(x1, y1, z1);
			rend.color(new Color(c2));
			rend.vertex(x2, y2, z2);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean line(Vector3 pos1, int c1, Vector3 pos2, int c2){
		if(pos1 == null || pos2 == null || !renderingLines) return false;
		return this.line(pos1.x, pos1.y, pos1.z, c1, pos2.x, pos2.y, pos2.z, c2);
	}
}
