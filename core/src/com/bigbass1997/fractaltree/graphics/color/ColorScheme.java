package com.bigbass1997.fractaltree.graphics.color;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

/**
 * A ColorScheme is applied to a tree to give it color.
 */
public interface ColorScheme {
	
	/**
	 * Called when generating the tree.
	 * 
	 * @param segments list of all segments that make up the tree
	 */
	public void invoke(ArrayList<Segment> segments);
	
}
