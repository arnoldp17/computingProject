package game2;

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
import game2.Level;
import game2.CordSet;

public class GameMain extends ApplicationAdapter {

	// SpriteBatch batch;
	Texture img;
	ShapeRenderer sr;
	CordSet gameSize = new CordSet(1000, 1000);
	Level game;

	@Override
	public void create() {
		game = new Level(10, 5, gameSize.getX(), gameSize.getY());
		// System.out.println("Created level");
		sr = new ShapeRenderer();
	}

	@Override
	public void render() {
		// System.out.println(1/Gdx.graphics.getDeltaTime()); //FPS TRACKER
		Gdx.gl.glBlendColor(0.45f, 0.45f, 0.45f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // sets the color for the background
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			System.exit(0);
		} // allows you to exit the program quickly by hitting the escape key }
		int i = 0;
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {

			i++;
			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GRAY);
		sr.rect(0, 0, gameSize.getX(), gameSize.getY());
		// System.out.println("type set");
		game.renderLvl(sr);
		// System.out.println("rendered level");
		sr.end();
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
