package com.gdxtemplate.game;

public class WallTile {
	private float Xcord;
	private float Ycord;
	private float Size;

	public WallTile(float x, float y, float s) {
		Xcord = x;
		Ycord = y;
		Size = s;
		System.out.println("TILES MADE");
	}

	public float getXcord() {
		return Xcord;
	}

	public float getYcord() {
		return Ycord;
	}

	public float getSZ() {
		return Size;
	}

	public void setXcord(float i) {
		Xcord = i;
	}

	public void setYcord(float i) {
		Ycord = i;
	}

	public void setSZ(float i) {
		Size = i;
	}
}
