package com.bigbass1997.fractaltree;

import java.util.ArrayList;
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
import com.bigbass1997.fractaltree.graphics.color.ColorSchemeLifelike;
import com.bigbass1997.fractaltree.graphics.growth.GrowSchemeNatural;
import com.bigbass1997.fractaltree.world.Forest;
import com.bigbass1997.fractaltree.world.Range;

public class Main extends ApplicationAdapter {
	
	public Stage stage;
	public Label debugLabel;
	private ImmediateModeRenderer20 render;
	private ShapeRenderer sr;
	public Forest forest;
	public static Camera cam;
	
	private boolean isForestRegenReady = true, isScreenshotReady = true;
	
	@Override
	public void create () {
		FontManager.addFont("fonts/computer.ttf"); //Added font to be used with Debug Text
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
		
		//Creates new stage for use with the debug text label
		stage = new Stage();
		
		debugLabel = new Label("", new Label.LabelStyle(FontManager.getFont("fonts/computer.ttf", 20).font, Color.WHITE));
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - debugLabel.getHeight());
		
		//Adds the debug label to the stage so that it can be rendered/updated
		stage.addActor(debugLabel);
		
		render = new ImmediateModeRenderer20(10000, false, true, 0);
		sr = new ShapeRenderer();
		
		forest = new Forest();
	}

	@Override
	public void render () {
		draw();
		update();
	}
	
	private void draw(){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		render.begin(cam.combined, ShapeType.Filled.getGlType());
		sr.begin(ShapeType.Filled);
		
		forest.render(render, sr);
		
		sr.end();
		render.end();
		
		stage.draw();
	}
	
	private void update(){
		Input input = Gdx.input;
		float delta = Gdx.graphics.getDeltaTime();
		
		if(input.isKeyPressed(Keys.SPACE) && isForestRegenReady){
			Range<Integer> genRange = new Range<Integer>(4, 4);
			ArrayList<Range<Float>> degChangeRanges = new ArrayList<Range<Float>>();
			for(int i = 0; i < 2; i++){
				degChangeRanges.add(new Range<Float>(65f, 115f));
			}
			
			Range<Float> initWidthRange = new Range<Float>(5f, 15f), initHeightRange = new Range<Float>(90f, 90f);
			Range<Float> widthMultRange = new Range<Float>(0.90f, 0.95f), heightMultRange = new Range<Float>(0.70f, 0.75f);
			
			Range<Integer> numTreesRange = new Range<Integer>(30, 40);
			
			forest.regenerate(genRange, degChangeRanges, initWidthRange, initHeightRange, widthMultRange, heightMultRange, numTreesRange, new ColorSchemeLifelike(), new GrowSchemeNatural());
			isForestRegenReady = false;
		} else if(!input.isKeyPressed(Keys.SPACE) && !isForestRegenReady){
			isForestRegenReady = true;
		}
		
		if(input.isKeyPressed(Keys.S) && isScreenshotReady){
			ScreenshotFactory.saveScreen();
			isScreenshotReady = false;
		} else if(!input.isKeyPressed(Keys.S) && !isScreenshotReady){
			isScreenshotReady = true;
		}
		
		forest.update(delta);
		
		//Debug Label Text Update
		String debugLabelText =
				"FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
				"Forest:\n" +
				"  Trees: " + forest.trees.size();
		
		debugLabel.setText(debugLabelText);
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - 75);
	}
	
	@Override
	public void dispose(){
		render.dispose();
	}
}
