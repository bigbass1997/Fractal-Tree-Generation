package com.bigbass1997.fractaltree.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bigbass1997.fractaltree.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1152;
		config.height = 700;
		config.resizable = false;
		
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		
		config.title = "Fractal Tree Generator";
		
		new LwjglApplication(new Main(), config);
	}
}
