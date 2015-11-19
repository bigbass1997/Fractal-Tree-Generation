package com.bigbass1997.fractaltree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.bigbass1997.fractaltree.world.Tree;

public class Main extends ApplicationAdapter {
	
	public Stage stage;
	public Label debugLabel;
	private ImmediateModeRenderer20 render;
	private ShapeRenderer sr;
	private Tree tree;
	public static Camera cam;
	
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
		
		tree = new Tree(3, 2);
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
		float speed = 50f * Gdx.graphics.getDeltaTime(); //allows for equal movement speed no matter the FPS
		
		tree.update(Gdx.graphics.getDeltaTime());
		
		debugLabel.setText(
				"FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
				"Speed: " + (int) (speed * Gdx.graphics.getFramesPerSecond()) + "px/s"
		);
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - debugLabel.getHeight() - 20);
	}
	
	@Override
	public void dispose(){
		render.dispose();
	}
}
