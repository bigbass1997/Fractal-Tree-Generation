package com.bigbass1997.fractaltree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bigbass1997.fractaltree.world.World;
import com.bigbass1997.fractaltree.fonts.FontManager;

public class Main extends ApplicationAdapter {
	
	public World world;
	public Stage stage;
	public Label debugLabel;
	
	@Override
	public void create () {
		FontManager.addFont("fonts/computer.ttf"); //Added font to be used with Debug Text
		
		//create, setup, and pass the camera that will be used. (Perspective or Orthogonal)
		PerspectiveCamera cam = new PerspectiveCamera(67f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(250f, 250f, 400f);
		cam.lookAt(250f, 250f, 0); //Maybe change to look directly at XY plane
		cam.near = 1f;
		cam.far = 5000f;
		cam.update();
		
		//Creates new world that holds all the objects that will be rendered and includes camera controls
		world = new World(cam);
		
		//Creates new stage for use with the debug text label
		stage = new Stage();
		
		debugLabel = new Label("", new Label.LabelStyle(FontManager.getFont("fonts/computer.ttf", 20).font, Color.BLACK));
		debugLabel.setPosition(10, Gdx.graphics.getHeight() - 110);
		
		//Adds the debug label to the stage so that it can be rendered/updated
		stage.addActor(debugLabel);
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
		
		world.render();
		stage.draw();
	}
	
	private void update(){
		float speed = 50f * Gdx.graphics.getDeltaTime(); //allows for equal movement speed no matter the FPS
		
		world.update(Gdx.graphics.getDeltaTime());
		
		debugLabel.setText(
				"FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
				"#Obs: " + world.objects.size() + "\n" + 
				"Speed: " + (int) (speed * Gdx.graphics.getFramesPerSecond()) + "px/s\n" + 
				"Cam Pos:\n" +
				"  X: " + world.cam.position.x + "\n" +
				"  Y: " + world.cam.position.y + "\n" +
				"  Z: " + world.cam.position.z + "\n" +
				"Cam Dir:\n" +
				"  X: " + world.cam.direction.x + "\n" +
				"  Y: " + world.cam.direction.y + "\n" +
				"  Z: " + world.cam.direction.z + "\n" +
				"Cam Up:\n" +
				"  X: " + world.cam.up.x + "\n" +
				"  Y: " + world.cam.up.y + "\n" +
				"  Z: " + world.cam.up.z
		);
	}
	
	@Override
	public void dispose(){
		world.dispose();
	}
}
