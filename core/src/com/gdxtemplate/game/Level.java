package com.gdxtemplate.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Level {
	Avatar player = new Avatar(500, 500, 0, 0, 15, 'M'); // defines the player and its attributes
	private ArrayList<Entity> foes = new ArrayList<Entity>();
	private int difficulty = 3; // Sets the difficulty multiplier
	private int blockMax; // sets the max number of walls
	private ArrayList<Walls> blocks = new ArrayList<Walls>();
	private int Xsize;
	private int Ysize;

	
	public Level(int difMult, int bMax, int X, int Y) {
		difficulty = difficulty *difMult; // will be used as the maximum value of enemies per rooms
		blockMax = bMax; // sets the maximum number of wall blocks
		Xsize = X;
		Ysize = Y;
	}

	public void createLevel() {
		for (int i = 0; i < getRNDI(blockMax); i++) {
			Walls segment = new Walls(1, 50, Xsize, Ysize);
			blocks.add(segment);
		}
		for (int i = 0; i < blockMax; i++) {
			blocks.get(i);
		}
		populate();
	}
	public void renderLvl() {
		System.out.println("LEVEL RENDER WORKS");
		for (int i = 0; i < blockMax; i++) {
//			blocks.get(i).render(i);
			System.out.println("Blocks rendered");
		}
	}
	public void populate() {
		int foeMax = difficulty;
		while (foeMax <=0) {
			Entity enemy = new Entity(10*foeMax, 100, 3, 3, 20);
			foes.add(enemy);
		}
	}
	
	
	
	
	
	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}


}
