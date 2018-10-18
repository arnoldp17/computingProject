package com.gdxtemplate.game;

import java.util.ArrayList;
import java.util.List;
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

public class ComputingProject extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	ShapeRenderer sr;
	int EntityMax = 5; // Sets the maximum number of entities
	int boxMax = 5; // sets the max number of boxes
	Avatar player = new Avatar(100, 100, 0, 0, 20, 'c');
	ArrayList<Entity> Foes = new ArrayList<Entity>(); // defines the foe list as an array type
	ArrayList<WallTile> Sqrs = new ArrayList<WallTile>(); // defines the box list as an array type
	// Gdx.app.getGraphics().getWidth();;
	// Gdx.app.getGraphics().getHeight();;

	@Override
	public void create() {
		batch = new SpriteBatch();
		// img = new Texture("sprite_00.png");

		for (int i = 0; i < EntityMax; i++) {
			Entity Enemy = new Entity(i * 60 + 20, 100 + i, 3, 3, 20);
			Foes.add(Enemy);
			// Creates each entity in the list as the same
		}
		for (int i = 0; i < boxMax; i++) {
			WallTile box = new WallTile(getRNDI(1000), getRNDI(1000) + 200, 200);
			Sqrs.add(box);
			// Creates each entity in the list as the same
		}

		sr = new ShapeRenderer();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // sets the color for the background
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			System.exit(0); // allows you to exit the program quickly by hitting the escape key
		}
		player.Motion();
		batch.begin();
		// batch.draw(img, 100, 100);
		batch.end();
		
		Color rect = new Color();
		rect.set(Color.GOLDENROD);
		sr.begin(ShapeType.Filled);
		for (int i = 0; i < EntityMax; i++) {
			Foes.get(i).render(); // draws the foes

		}
		sr.setColor(rect);
		for (int i = 0; i < boxMax; i++) {
			sr.rect(((Sqrs.get(i))).getXcord(), (Sqrs.get(i)).getYcord(), (Sqrs.get(i)).getSZ(), (Sqrs.get(i)).getSZ());
			// draws all the rectangles
		}
		sr.circle(player.getXcord(), player.getYcord(), player.getSZ());

		sr.end();
		for (int i = 0; i < EntityMax; i++) {
			Foes.get(i).travel(); // collision with the WallTile of the screen + motion for enemies
		}
		

		for (int i = 0; i < EntityMax; i++) { // collision of foes with what will be WallTile
			for (int j = 0; j < boxMax; j++) {
				if (boxCollide((Foes.get(i)).getXcord(), (Foes.get(i)).getYcord(), (Foes.get(i)).getSZ(),
						(Sqrs.get(j)).getXcord(), (Sqrs.get(j)).getYcord(), (Sqrs.get(j)).getSZ()) == true) {
					(Foes.get(i)).setSpdX((Foes.get(i)).getSpdX() * -1);
					(Foes.get(i)).setSpdY((Foes.get(i)).getSpdY() * -1);

				}
			}
			for (int k = 0; k < EntityMax; k++) {

				if (i != k) { // collision between foes
					if (circCollide((Foes.get(i)).getXcord(), (Foes.get(i)).getYcord(), (Foes.get(i)).getSZ(),
							(Foes.get(k)).getXcord(), (Foes.get(k)).getYcord(), (Foes.get(k)).getSZ()) == true) {

						(Foes.get(i)).setSpdX((Foes.get(i)).getSpdX() * -1);
						(Foes.get(k)).setSpdY((Foes.get(k)).getSpdY() * -1);

					}
				}
			}
		}
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

	public static boolean circCollide(float X1, float Y1, float S1, float X2, float Y2, float S2) {
		float adj = Math.abs(X1 - X2);
		float ops = Math.abs(Y1 - Y2);
		float hype = (adj * adj) + (ops * ops);
		hype = (float) Math.sqrt(hype);
		boolean collide = false;
		if (hype <= (S1 + S2)) {
			collide = true;

		}

		return collide;
	}

	public static boolean boxCollide(float X1, float Y1, float S1, float X2, float Y2, float S2) {
		boolean collide = false;
		float lftSd = X1 - S1;
		float rghtSd = X1 + S1;
		float ovr = Y1 - S1;
		float undr = Y1 + S1;
		if (lftSd <= X2 + S2 && rghtSd >= X2) {
			if (Y2 + S2 >= ovr && undr >= Y2) {
				collide = true;

			}

		}

		return collide;

	}
}