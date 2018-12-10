package game2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import game2.WallTile;

public class Walls {
	private int numOfFile;
	private ArrayList<WallTile> block = new ArrayList<WallTile>();
	private int Xcord;
	private int Ycord;
	private int SizeX;
	private int SizeY;

	List<String> Lvls = new ArrayList<String>(); // defines the list as an array type

	public Walls(int i, int sX, int sY, int width, int height) {
		numOfFile = i;
		Lvls.add("H:\\\\Eclipse Java Workspace\\\\leveltype1.csv");
		Lvls.add("H:\\\\Eclipse Java Workspace\\\\leveltype2.csv");
		Lvls.add("H:\\\\Eclipse Java Workspace\\\\leveltype3.csv");
		for (int j = 0; j < numOfFile; j++) {
			addWall();
		}

	}

	public void addWall() {
		try {
//			for (int j = 0; j < numOfFile; j++) {
				File file = new File(Lvls.get(0));

				Scanner lvl = new Scanner(file);

				while (lvl.hasNextLine()) {
					String line = lvl.nextLine();
					String[] parts = line.split(",");

					WallTile segment = new WallTile(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]),
							Float.parseFloat(parts[2]), Float.parseFloat(parts[3]));
					block.add(segment);

				}
				lvl.close();
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void renderBlock(ShapeRenderer sr) {
		// System.out.println("in block render");
		sr.setColor(Color.YELLOW);
		for (int i = 0; i < block.size(); i++) {
			// System.out.println("in tile render loop");
			block.get(i).render(sr);
			// System.out.println("rendered tile");
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
	public int size() {
		return block.size();
	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}
}