package com.bigbass1997.fractaltree.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bigbass1997.fractaltree.world.Object;

public class Segment extends Object {
	
	public Segment(Vector3 pos, Vector2 dim, int color){
		this.pos = pos;
		this.size = new Vector3(dim.x, dim.y, 0.01f);
		this.color = color;
		
		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(size.x, size.y, size.z,
				new Material(ColorAttribute.createDiffuse(new Color(color))),
				Usage.Position | Usage.Normal);
		
		modelInstance = new ModelInstance(model);
		
		this.setPos(pos);
	}
}
