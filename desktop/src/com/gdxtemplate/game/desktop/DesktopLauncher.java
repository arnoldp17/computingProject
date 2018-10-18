package com.gdxtemplate.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import com.gdxtemplate.game.ComputingProject;
//import com.gdxtemplate.game.Gamemain;

import game2.GameMain;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// config.fullscreen = true;
		new LwjglApplication(new GameMain(), config);

		config.height = 1000;
		// Gdx.app.getGraphics().getHeight();
		config.width = 1000;
		// Gdx.app.getGraphics().getWidth();
		config.foregroundFPS = 60;

		// config.backgroundFPS = 60;

	}
}
