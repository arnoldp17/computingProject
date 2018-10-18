package com.gdxtemplate.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Gamemain extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	ShapeRenderer sr;
	Sizer gameSize = new Sizer(1000, 1000);
	Level game;

	@Override
	public void create() {
		Level game = new Level(10, 1, gameSize.getX(), gameSize.getY());
		System.out.println("Created level");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.45f, 0.45f, 0.45f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // sets the color for the background
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			System.exit(0); // allows you to exit the program quickly by hitting the escape key
		}
		System.out.println("just before render");
		game.renderLvl();
		//game.setLvlNo(0);
		//game.ren();
	}

	@Override
	public void dispose() {
		batch.dispose();
		// img.dispose();

	}

	public static float getRNDF(float i) {
		float rnd = new Random().nextFloat();
		return rnd;
	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}

}
