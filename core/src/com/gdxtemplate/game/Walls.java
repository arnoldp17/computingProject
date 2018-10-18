package com.gdxtemplate.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Walls {
	private int tileNo;
	private ArrayList<WallTile> block = new ArrayList<WallTile>();
	private int Xcord;
	private int Ycord;
	private int Size;
	ShapeRenderer sr = new ShapeRenderer();

	public Walls(int i, int s, int sizeX, int sizeY) {
		Size = s;
		tileNo = i;
		Xcord = getRNDI(sizeX);
		Ycord = getRNDI(sizeY);
		for (int j = 0; j < tileNo; j++) {
			addWall(Xcord, Ycord, Size);
		}

	}

	public void addWall(int x, int y, int s) {
		int LorR = getRNDI(6);
		int UorD = getRNDI(6);
		WallTile segment = new WallTile(Xcord, Ycord, Size);
		block.add(segment);
		if (LorR < 4) {
			Xcord = Xcord - Size;
		} else {
			Xcord = Xcord + Size;
		}
		if (UorD < 4) {
			Ycord = Ycord - Size;
		} else {
			Ycord = Ycord + Size;
		}

	}

	public void render(int i) {
		System.out.println("WALL RENDER WORKS");
		for (int j = 0; j < tileNo; j++) {
			ShapeRenderer sr = new ShapeRenderer();
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.YELLOW);
			sr.rect(((block.get(i))).getXcord(), (block.get(i)).getYcord(), (block.get(i)).getSZ(),
					(block.get(i)).getSZ());
			sr.end();
		}
	}

	public void ell() {

	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}
}
