package com.bigbass1997.fractaltree.graphics.growth;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

public class GrowThread extends Thread {
	
	public ArrayList<Segment> segments;
	
	public GrowThread(ArrayList<Segment> segments){
		this.segments = segments;
	}
}
