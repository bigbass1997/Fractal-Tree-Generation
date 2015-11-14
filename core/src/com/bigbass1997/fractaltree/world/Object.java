package com.bigbass1997.fractaltree.world;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Object {
	
	protected Vector3 pos;
	public Vector3 size;
	
	public Model model;
	public ModelInstance modelInstance;
	
	public int color;
	
	/**
	 * <p>Creates new base Object at origin with a size of 0.</p>
	 */
	public Object(){
		this(Vector3.Zero, Vector3.Zero);
	}
	
	/**
	 * <p>Creates new base Object at provided x,y,z with a size of 0.</p>
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Object(float x, float y, float z){
		this(new Vector3(x, y, z), Vector3.Zero);
	}
	
	/**
	 * <p>Creates new base Object at provided Vector3 with a size of 0.</p>
	 * 
	 * @param pos
	 * 
	 * @see Vector3
	 */
	public Object(Vector3 pos){
		this(pos, Vector3.Zero);
	}
	
	/**
	 * <p>Creates new base Object at provided x,y,z with the size provided through xlen,ylen,zlen.</p>
	 * 
	 * @param x	Position along the x axis.
	 * @param y	Position along the y axis.
	 * @param z	Position along the z axis.
	 * @param xlen Length of object along the x axis.
	 * @param ylen Length of object along the y axis.
	 * @param zlen Length of object along the z axis.
	 */
	public Object(float x, float y, float z, float xlen, float ylen, float zlen){
		this(new Vector3(x, y, z), new Vector3(xlen, ylen, zlen));
	}
	
	/**
	 * <p>Creates new base Object at provided z,y,z with the size provided through a Vector3.</p>
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param size The x,y,z values of this Vector3 will determine the size of the object along the respective axes.
	 * 
	 * @see Vector3
	 */
	public Object(float x, float y, float z, Vector3 size){
		this(new Vector3(x, y, z), size);
	}
	
	/**
	 * <p>Creates new base Object at provided Vector3 position with the size provided through a second Vector3.</p>
	 * 
	 * @param pos 
	 * @param size The x,y,z values of this Vector3 will determine the size of the object along the respective axes.
	 * 
	 * @see Vector3
	 */
	public Object(Vector3 pos, Vector3 size){
		this.pos = pos;
		this.size = size;
		this.color = 0x000000FF;
	}
	
	public void setPos(Vector3 pos){
		this.setPos(pos.x, pos.y, pos.z);
	}
	
	public void setPos(float x, float y, float z){
		pos.x = x;
		pos.y = y;
		pos.z = z;
		modelInstance.transform.val[Matrix4.M03] = x;
		modelInstance.transform.val[Matrix4.M13] = y;
		modelInstance.transform.val[Matrix4.M23] = z;
	}
	
	public Vector3 getPos(){
		return this.pos;
	}
	
	public void addPos(Vector3 addPos){
		this.addPos(addPos.x, addPos.y, addPos.z);
	}
	public void addPos(float x, float y, float z){
		pos.add(x, y, z);
		modelInstance.transform.val[Matrix4.M03] += x;
		modelInstance.transform.val[Matrix4.M13] += y;
		modelInstance.transform.val[Matrix4.M23] += z;
	}
	
	public void render(){
		
	}
	
	public void update(float delta){
		
	}
	
	public void dispose(){
		model.dispose();
	}
}
