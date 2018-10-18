package com.gdxtemplate.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGDXGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;

	ShapeRenderer sr;

	public static int entities = 6;
	public int[][] posArray = new int[entities][2];
	public char[] dirLR = new char[entities];
	public char[] dirUD = new char[entities];
	public static int size = 50;

	// public int Width = Gdx.app.getGraphics().getWidth();;
	// public int Height = Gdx.app.getGraphics().getHeight();;
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sr = new ShapeRenderer();

		// fill pos array
		for (int i = 0; i < entities; i++) {
			posArray[i][0] = size + getRND(Gdx.app.getGraphics().getWidth() - (size * 2));
			posArray[i][1] = size + getRND(Gdx.app.getGraphics().getHeight() - (size * 2));
		}

		for (int i = 0; i < entities; i++) {
			dirLR[i] = 'R';
			dirUD[i] = 'D';
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.45f, 0.45f, 0.45f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			System.exit(0);
		}
		/*
		 * if (Gdx.input.isKeyPressed(Keys.UP)) { circY = circY + 1; } if
		 * (Gdx.input.isKeyPressed(Keys.DOWN)) { circY = circY - 1; }
		 * 
		 * if (Gdx.input.isKeyPressed(Keys.RIGHT)) { circX = circX + 1; } if
		 * (Gdx.input.isKeyPressed(Keys.LEFT)) { circX = circX - 1; }
		 */

		batch.begin();
		// batch.draw(img, x, y);
		batch.end();
		Color red = new Color();
		red.set(Color.FIREBRICK);

		sr.begin(ShapeType.Filled);
		for (int i = 0; i < posArray.length; i++) {
			sr.setColor(red);
			sr.circle(posArray[i][0], posArray[i][1], size);
		}
		for (int i = 0; i < entities; i++) {
			if (dirLR[i] == 'L') {
				int oldpos = posArray[i][0];
				posArray[i][0] = moveL(posArray[i][0]);
				if (oldpos == posArray[i][0]) {
					dirLR[i] = 'R';
				}
			}
			if (dirLR[i] == 'R') {
				int oldpos = posArray[i][0];
				posArray[i][0] = moveR(posArray[i][0]);
				if (oldpos == posArray[i][0]) {
					dirLR[i] = 'L';
				}
			}
			if (dirUD[i] == 'U') {
				int oldpos = posArray[i][1];
				posArray[i][1] = moveU(posArray[i][1]);
				if (oldpos == posArray[i][1]) {
					dirUD[i] = 'D';
				}
			}
			if (dirUD[i] == 'D') {
				int oldpos = posArray[i][1];
				posArray[i][1] = moveD(posArray[i][1]);
				if (oldpos == posArray[i][1]) {
					dirUD[i] = 'U';
				}
			}
			boolean hit = false;
			
			
			
			
			
			for (int l = 0; l < entities; l++) {
			if (circCollide(posArray[i][0], posArray[l][0],posArray[i][1], posArray[i][1])==true) {
				
				}
			}
			

			for (int l = 0; l < entities; l++) {
				if (l != i) {
					hit = circCollide(posArray[i][0], posArray[l][0],posArray[i][1], posArray[i][1]);
					if (hit == true) {
						if (dirLR[i] == 'L') {
							dirLR[i] = 'R';
						} else {
							if (dirLR[i] == 'R') {
								dirLR[i] = 'L';
							}
						}
						if (dirUD[i] == 'D') {
							dirUD[i] = 'U';
						} else {
							if (dirUD[i] == 'U') {
								dirUD[i] = 'D';
							}
						}
					}
				}
			}
		}

		sr.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	public static int getRND(int i) {
		int rnd = new Random().nextInt(i) + 1;
		return rnd;
	}

	public static int moveR(int x) {

		if (x < Gdx.app.getGraphics().getWidth() - size) {
			x = x + 1;
		}
		return x;
	}

	public static int moveL(int x) {
		if (x > size) {
			x = x - 1;
		}
		return x;

	}

	public static int moveD(int x) {
		if (x > size) {
			x = x - 1;
		}
		return x;

	}

	public static int moveU(int x) {

		if (x < Gdx.app.getGraphics().getHeight() - size) {
			x = x + 1;
		}
		return x;
	}

	public static boolean circCollide(int X, int X2, int Y, int Y2) {
		int adj = X - X2;
		int opr = Y - Y2;
		float hype = (adj * adj) + (opr * opr);
		hype = (float) Math.sqrt(hype);
		boolean collide= hype<=size*2;
	/*	if (hype <= size * 2) {
			struck = true;
		}
*/		return collide;
	}
}
