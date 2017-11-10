package com.bigbass1997.fractaltree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.bigbass1997.fractaltree.world.TreeController;

public class Main extends ApplicationAdapter {
	
	public Stage stage;
	public Label debugLabel;
	private ImmediateModeRenderer20 render;
	private ShapeRenderer sr;
	public TreeController treeController;
	public static Camera cam;
	
	private boolean isScreenshotReady = true;
	
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
		
		treeController = new TreeController();
		
		//Temporary until GUI is created
		treeController.setGenerationsRange(6, 10);
		treeController.setDegreeChangesRanges(55, 125, 65, 115);
		treeController.setInitWidthRange(6, 8);
		treeController.setInitHeightRange(120, 120);
		treeController.setWidthMultiplierRange(0.80f, 0.95f);
		treeController.setHeightMultiplierRange(0.85f, 0.90f);
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
		
		treeController.render(render, sr);
		
		sr.end();
		render.end();
		
		stage.draw();
	}
	
	private void update(){
		float delta = Gdx.graphics.getDeltaTime();
		
		//Allows taking a screenshot of whatever is on the screen
		if(Gdx.input.isKeyPressed(Keys.S) && isScreenshotReady){
			ScreenshotFactory.saveScreen();
			isScreenshotReady = false;
		} else if(!Gdx.input.isKeyPressed(Keys.S) && !isScreenshotReady){
			isScreenshotReady = true;
		}
		
		treeController.update(delta);
		
		//Debug Label Text Update
		String debugLabelText =
				"FPS: " + Gdx.graphics.getFramesPerSecond();
		
		debugLabel.setText(debugLabelText);
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - 75);
	}
	
	@Override
	public void dispose(){
		render.dispose();
	}
}
