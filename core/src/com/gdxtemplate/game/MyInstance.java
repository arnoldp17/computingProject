package com.gdxtemplate.game;

import java.util.ArrayList;

public class MyInstance {
	Avatar player = new Avatar(500, 500, 0, 0, 15, 'M'); // defines the player and its attributes
	private int levelNo; // Defines the number of levels, and also the difficulty multiplier
	ArrayList<Level> levelList = new ArrayList<Level>();
	private int levelOn = 1;

	public MyInstance(int LvlNo, int NoofLvl, int SizeX, int SizeY) {
		System.out.println("Instance made");
		levelNo = LvlNo;
		//for (int i = 0; i < NoofLvl; i++) { // creates a list of all the levels the game will use
			Level newLevel = new Level(LvlNo, 10, SizeX, SizeY);
		//	levelList.add(NewLevel);
		}
	//}

	public void setLvlNo(int lvlNo) {
		levelNo = lvlNo;
	}


	public float getLvlNo() {
		return levelNo;
	}

	public void LvlUp() {
		levelOn++;
	}

	public void ren() {
		// TODO Auto-generated method stub
		System.out.println("Blob");
	}

//	public void renderAll() {
//		System.out.println("SUPER RENDER WORKS");
//		levelList.get(levelOn).renderLvl();
//	}
}
