package com.bigbass1997.fractaltree.graphics.growth;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

public interface GrowScheme {
	
	public void invoke(ArrayList<Segment> segments);
	
}
