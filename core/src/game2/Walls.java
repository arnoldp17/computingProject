package game2;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import game2.WallTile;

public class Walls {
	private int tileNo;
	private ArrayList<WallTile> block = new ArrayList<WallTile>();
	private int Xcord;
	private int Ycord;
	private int SizeX;
	private int SizeY;

	public Walls(int i, int sX, int sY, int width, int height) {
		tileNo = i;
		SizeX = sX;
		SizeY = sY;
		Xcord = getRNDI(width);
		Ycord = getRNDI(height);
		for (int j = 0; j < tileNo; j++) {
			addWall(Xcord, Ycord);
		}

	}

	public void addWall(int x, int y) {
		int LorR = getRNDI(100);
		int UorD = getRNDI(100);
		int Dir = getRNDI(100);
		WallTile segment = new WallTile(Xcord, Ycord, SizeX, SizeY);
		block.add(segment);
		if (Dir % 3 == 0) {
			if (LorR % 3 == 0) {
				Xcord = Xcord - SizeX;
				System.out.println("Left");
			} else {
				Xcord = Xcord + SizeX;
				System.out.println("Right");
			}
		} else {
			if (UorD % 3 == 0) {
				Ycord = Ycord - SizeY;
				System.out.println("Down");
			} else {
				Ycord = Ycord + SizeY;
				System.out.println("Up");
			}

		}

	}

	public void renderBlock(ShapeRenderer sr) {
		System.out.println("in block render");
		sr.setColor(Color.YELLOW);
		for (int i = 0; i < tileNo; i++) {
			System.out.println("in tile render loop");
			block.get(i).render(sr);
			System.out.println("rendered tile");
		}
	}

	public float getTileDataX(int i) {
		return block.get(i).getXcord();
	}
	public float getTileDataY(int i) {
		return block.get(i).getYcord();
	}
	public float getTileDataSZX(int i) {
		return block.get(i).getSZX();
	}
	public float getTileDataSZY(int i) {
		return block.get(i).getSZY();
	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}
}