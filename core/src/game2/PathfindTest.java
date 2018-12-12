package game2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PathfindTest extends ApplicationAdapter {

	CordSet gameSize = new CordSet(1000, 1000);
	Walls block = new Walls(1, 20, 50, gameSize.getX(), gameSize.getY());
	Graph graph = new Graph(gameSize.getX(), gameSize.getY(), 10, block);
	ShapeRenderer sr;
	float countDown = 2;

	public void create() {
		sr = new ShapeRenderer();

	}

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
				e.printStackTrace();
			}
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GRAY);
		sr.rect(0, 0, gameSize.getX(), gameSize.getY());
		block.renderBlock(sr);
		Node start = new Node(400, 300, 50, false);
		Node end = new Node(600, 200, 50, false);

		graph.renderGraph(sr);
		sr.setColor(Color.RED);
		sr.rect(start.getX(), start.getX(), start.getCost(), start.getCost());
		sr.setColor(Color.BLUE);
		sr.rect(end.getX(), end.getX(), end.getCost(), end.getCost());

		if (countDown >= 1) {
			countDown = countDown - (Gdx.graphics.getDeltaTime());
			System.out.println(countDown);
		} else {
			System.out.println(graph.pathfind(start, end, sr, block)); //PATH FINDING IS HERE
			countDown = 2;
			System.out.println("Path found");
		}

		sr.end();
	}

}
