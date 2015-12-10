package com.bigbass1997.fractaltree;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bigbass1997.fractaltree.fonts.FontManager;
import com.bigbass1997.fractaltree.graphics.TreeOfLifeColorScheme;
import com.bigbass1997.fractaltree.world.Tree;

public class Main extends ApplicationAdapter {
	
	public Stage stage;
	public Label debugLabel;
	private ImmediateModeRenderer20 render;
	private ShapeRenderer sr;
	private Tree tree;
	public static Camera cam;
	
	private boolean isTreeRegenReady = true, isScreenshotReady = true;
	
	@Override
	public void create () {
		FontManager.addFont("fonts/computer.ttf"); //Added font to be used with Debug Text
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
		
		//Creates new stage for use with the debug text label
		stage = new Stage();
		
		debugLabel = new Label("", new Label.LabelStyle(FontManager.getFont("fonts/computer.ttf", 20).font, Color.BLACK));
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - debugLabel.getHeight());
		
		//Adds the debug label to the stage so that it can be rendered/updated
		stage.addActor(debugLabel);
		
		render = new ImmediateModeRenderer20(5000, false, true, 0);
		sr = new ShapeRenderer();
		
		tree = new Tree(0, new float[]{}, 0, 0, 0, 0, new TreeOfLifeColorScheme());
	}

	@Override
	public void render () {
		draw();
		update();
	}
	
	private void draw(){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		render.begin(cam.combined, ShapeType.Filled.getGlType());
		sr.begin(ShapeType.Filled);
		
		tree.render(render, sr);
		
		sr.end();
		render.end();
		
		stage.draw();
	}
	
	private void update(){
		Input input = Gdx.input;
		
		if(input.isKeyPressed(Keys.SPACE) && isTreeRegenReady){
			Random rand = new Random();
			
			int generations = rand.nextInt(5) + 4; //4-8
			float[] degreeChanges = new float[]{((rand.nextFloat() * 160) + 10),((rand.nextFloat() * 160) + 10),((rand.nextFloat() * 160) + 10)}; //10-170
			float initWidth = (rand.nextFloat() * 8) + 5; //8-13
			float initHeight = (rand.nextFloat() * 70) + 50; //50-120
			float heightMultiplier = (rand.nextFloat() * 0.35f) + 0.6f; //0.60-0.95
			float widthMultiplier = (rand.nextFloat() * 0.30f) + 0.6f; //0.60-0.90
			
			tree = new Tree(generations, degreeChanges, initWidth, initHeight, heightMultiplier, widthMultiplier, new TreeOfLifeColorScheme());
			isTreeRegenReady = false;
		} else if(!input.isKeyPressed(Keys.SPACE) && !isTreeRegenReady){
			isTreeRegenReady = true;
		}
		
		if(input.isKeyPressed(Keys.S) && isScreenshotReady){
			ScreenshotFactory.saveScreen(tree);
			isScreenshotReady = false;
		} else if(!input.isKeyPressed(Keys.S) && !isScreenshotReady){
			isScreenshotReady = true;
		}
		
		tree.update(Gdx.graphics.getDeltaTime());
		
		String debugLabelText =
				"FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
				"Tree:\n" +
				"  generations: " + tree.generations + "\n" +
				"  degreeChanges: ";
		
		for(int i = 0; i < tree.degreeChanges.length; i++){
			debugLabelText += (tree.degreeChanges[i] + " ");
		}
		
		debugLabelText +=
				"\n  initWidth: " + tree.initWidth + "\n" +
				"  initHeight: " + tree.initHeight + "\n" +
				"  widthMult: " + tree.widthMultiplier + "\n" +
				"  heightMult: " + tree.heightMultiplier + "\n" +
				"  segments: " + tree.segments.size();
		
		debugLabel.setText(debugLabelText);
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - 75);
	}
	
	@Override
	public void dispose(){
		render.dispose();
	}
}
