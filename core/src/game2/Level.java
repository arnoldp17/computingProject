package game2;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game2.Avatar;
import game2.Entity;
import game2.Walls;

public class Level {
	private Avatar player = new Avatar(500, 500, 0, 0, 15, 'M'); // defines the player and its attributes
	private ArrayList<Entity> foes = new ArrayList<Entity>(); // creates a list for enemies
	private ArrayList<Walls> blocks = new ArrayList<Walls>(); // creates a list for wall blocks
	private int difficulty = 3; // Sets the difficulty multiplier
	private int tileMax = 5; // sets the max number of tiles per block
	private int blockMax; // sets the max number of walls

	private int Xsize;
	private int Ysize;

	public Level(int difMult, int bMax, int X, int Y) {
		// difficulty = difMult * difficulty; // will be used as the maximum value of
		// enemies per rooms
		blockMax = bMax; // sets the maximum number of wall blocks
		Xsize = X;
		Ysize = Y;
		createLevel();
	}

	public void createLevel() {
		for (int i = 0; i < blockMax; i++) {
			Walls block = new Walls(tileMax, 20, Xsize, Ysize);
			blocks.add(block);
		}

		populate();
	}

	public void renderLvl(ShapeRenderer sr) {
		player.Motion();
		player.renderAvatar(sr);
		System.out.println("rendered block");
		for (int j = 0; j < blockMax; j++) {
			System.out.println("In loop");
			blocks.get(j).renderBlock(sr);
			System.out.println("rendered blocks No " + j);
		}
		for (int i = 0; i < difficulty; i++) {
			System.out.println("In entity render");
			foes.get(i).renderEntity(sr);
			System.out.println("rendered entity No " + i);
			foes.get(i).travel();
		}
		entityCollisions();

	}

	private void entityCollisions() {
		boxCollision();
		for (int i = 0; i < difficulty; i++) {
			if (circCollide((foes.get(i)).getXcord(), (foes.get(i)).getYcord(), (foes.get(i)).getSZ(),
					player.getXcord(), player.getYcord(), player.getSZ()) == true) {

				(foes.get(i)).setSpdX((foes.get(i)).getSpdX() * -1);
			//	(foes.get(i)).setSpdY((foes.get(i)).getSpdY() * -1);

			}
			for (int k = 0; k < difficulty; k++) {
				if (i != k) { // collision between foes
					if (circCollide((foes.get(i)).getXcord(), (foes.get(i)).getYcord(), (foes.get(i)).getSZ(),
							(foes.get(k)).getXcord(), (foes.get(k)).getYcord(), (foes.get(k)).getSZ()) == true) {

						(foes.get(i)).setSpdX((foes.get(i)).getSpdX() * -1);
						(foes.get(k)).setSpdY((foes.get(k)).getSpdY() * -1);

					}
				}
			}
		}
		
	}

	private boolean circCollide(float xcord, float ycord, float sz, float xcord2, float ycord2, float sz2) {
		float adj = Math.abs(xcord - xcord2);
		float ops = Math.abs(ycord - ycord2);
		float hype = (adj * adj) + (ops * ops);
		hype = (float) Math.sqrt(hype);
		boolean collide = false;
		if (hype <= (sz + sz2)) {
			collide = true;

		}

		return collide;
	}


	private void boxCollision() {
		for (int j = 0; j < blockMax; j++) {
			for (int k = 0; k < tileMax; k++) {

				float X1 = player.getXcord();
				float S1 = player.getSZ();
				float Y1 = player.getYcord();
				float X2 = blocks.get(j).getTileDataX(k);
				float Y2 = blocks.get(j).getTileDataY(k);
				float S2 = blocks.get(j).getTileDataSZ(k);
				float lftSd = X1 - S1;
				float rghtSd = X1 + S1;
				float ovr = Y1 - S1;
				float undr = Y1 + S1;
				if (lftSd <= X2 + S2 && rghtSd >= X2) {
					if (Y2 + S2 >= ovr && undr >= Y2) {

						player.setXcord(player.getXcord() + 5);
						player.setYcord(player.getYcord() + 5);

					}
				}
			}
		}
		
		for (int i = 0; i < difficulty; i++) {
			for (int j = 0; j < blockMax; j++) {
				for (int k = 0; k < tileMax; k++) {

					float X1 = foes.get(i).getXcord();
					float S1 = foes.get(i).getSZ();
					float Y1 = foes.get(i).getYcord();
					float X2 = blocks.get(j).getTileDataX(k);
					float Y2 = blocks.get(j).getTileDataY(k);
					float S2 = blocks.get(j).getTileDataSZ(k);
					float lftSd = X1 - S1;
					float rghtSd = X1 + S1;
					float ovr = Y1 - S1;
					float undr = Y1 + S1;
					if (lftSd <= X2 + S2 && rghtSd >= X2) {
						if (Y2 + S2 >= ovr && undr >= Y2) {
							foes.get(i).setSpdX(foes.get(i).getSpdX() * -1);
							foes.get(i).setSpdY(foes.get(i).getSpdY() * -1);
						}

					}
				}
			}

		}

	}


	public void populate() {
		for (int f = 0; f < difficulty; f++) {
			Entity enemy = new Entity((f * 50) + 30, 100, 1, 1, 20);
			foes.add(enemy);
		}
	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}

}
