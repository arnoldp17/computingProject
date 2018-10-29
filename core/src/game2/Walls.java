package game2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
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
	String leveltypes = "H:\\\\Eclipse Java Workspace\\leveltype1.csv";
/*	String[] leveltypes;
	{
		leveltypes = new String[1];

		
	} */

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
		try {

			File file = new File(leveltypes);

			Scanner in = new Scanner(file);

			while (in.hasNextLine()) {
				for (int i = 0; i < 6; i++) {
					String line = in.nextLine();
					String[] parts = line.split(",");
					
					WallTile segment = new WallTile(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
					block.add(segment);
				}
				in.close();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
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