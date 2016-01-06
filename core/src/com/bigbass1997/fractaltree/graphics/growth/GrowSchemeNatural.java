package com.bigbass1997.fractaltree.graphics.growth;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.bigbass1997.fractaltree.world.Segment;

public class GrowSchemeNatural implements GrowScheme {

	@Override
	public void invoke(ArrayList<Segment> segments) {
		new GrowThread(segments){
			
			@Override
			public void run(){
				double increaseSpeed = 0.00025f * Gdx.graphics.getDeltaTime();
				
				ArrayList<ArrayList<Segment>> segmentMap = new ArrayList<ArrayList<Segment>>();
				for(int i = 0; i < segments.get(0).level + 1; i++){
					segmentMap.add(new ArrayList<Segment>());
				}
				
				for(Segment segment : segments){
					segmentMap.get(segment.level).add(segment);
				}
				
				//System.out.println("segmentMap.size()=" + segmentMap.size());
				for(int i = segmentMap.size(); i > 0; i--){
					//System.out.println((i-1) + " | segmentMap.get(i).size()=" + segmentMap.get(i - 1).size());
					
					double levelIncreaseSpeed = increaseSpeed * segmentMap.get(i-1).size();
					System.out.println(levelIncreaseSpeed);
					Segment levelSegment = segmentMap.get(i-1).get(0);
					while(levelSegment.size.y < levelSegment.maxHeight){
						//System.out.println(levelSegment.size.y + " / " + levelSegment.maxHeight);
						if(levelSegment.size.y + levelIncreaseSpeed > levelSegment.maxHeight){
							for(Segment segment : segmentMap.get(i-1)){
								segment.size.y = segment.maxHeight;
							}
						}else{
							for(Segment segment : segmentMap.get(i-1)){
								segment.size.y += levelIncreaseSpeed;
							}
						}
					}
					/*
					for(Segment segment : segmentMap.get(i - 1)){
						while(segment.size.y < segment.maxHeight){
							//System.out.println(segment.size.y + " / " + segment.maxHeight);
							if(segment.size.y + increaseSpeed > segment.maxHeight) segment.size.y = segment.maxHeight;
							else {
								segment.size.y += increaseSpeed;
							}
							//System.out.println((i-1) + " | " + segment.level + " | " + segment.size.y + " | " + segment.maxHeight);
						}
						//System.out.println((i-1) + " | " + segment.size.y + " / " + segment.maxHeight);
					}*/
				}
			}
			
		}.start();
	}
}
