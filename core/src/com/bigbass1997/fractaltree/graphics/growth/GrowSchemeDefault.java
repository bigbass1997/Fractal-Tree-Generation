package com.bigbass1997.fractaltree.graphics.growth;

import java.util.ArrayList;

import com.bigbass1997.fractaltree.world.Segment;

public class GrowSchemeDefault implements GrowScheme {

	@Override
	public void invoke(ArrayList<Segment> segments) {
		new GrowThread(segments){
			
			@Override
			public void run(){
				for(Segment segment : segments){
					segment.size.y = segment.maxHeight;
				}
			}
			
		}.start();
	}
}
