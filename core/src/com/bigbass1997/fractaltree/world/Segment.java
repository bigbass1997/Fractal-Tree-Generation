package com.bigbass1997.fractaltree.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
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
	
	private Vector3 tmpVec;
	
	public int level;
	
	public Segment(Vector3 pos, Vector2 dim, int color, float degrees){
		this(pos, dim, color, degrees, 0);
	}
	public Segment(Vector3 pos, Vector2 dim, int color, float degrees, int level){
		this.pos = pos.add(0, dim.y/2, 0);
		this.size = new Vector3(dim.x, dim.y, 0.01f);
		this.color = color;
		this.level = level;
		
		tmpVec = new Vector3();
		
		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(size.x, size.y, size.z,
				new Material(ColorAttribute.createDiffuse(new Color(color))),
				Usage.Position | Usage.Normal);
		
		modelInstance = new ModelInstance(model);
		
		this.setPos(pos);
		
		rotate(degrees);
	}
	
	public void rotate(float degrees){
		tmpVec = getPos().cpy();
		modelInstance.transform.translate(0, -tmpVec.y, -tmpVec.z).rotate(Vector3.Z, -degrees).translate(0, tmpVec.y, tmpVec.z);
	}
	
	@Override
	public void update(float delta){
		Input input = Gdx.input;
		
		float speed = 50f * delta;
		
		if(input.isKeyPressed(Keys.O)){ //Rotates Segment counter-clockwise
			Vector3 tmpPos = getPos().cpy();
			modelInstance.transform.translate(0, -tmpPos.y, -tmpPos.z).rotate(Vector3.Z, speed*2).translate(0, tmpPos.y, tmpPos.z);
		}
		if(input.isKeyPressed(Keys.P)){ //Rotates Segment clockwise
			Vector3 tmpPos = getPos().cpy();
			modelInstance.transform.translate(0, -tmpPos.y, -tmpPos.z).rotate(Vector3.Z, -speed*2).translate(0, tmpPos.y, tmpPos.z);
		}
	}
}
