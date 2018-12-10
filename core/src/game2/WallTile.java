package game2;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WallTile {
	private float Xcord;
	private float Ycord;
	private float SizeX;
	private float SizeY;

	public WallTile(float x, float y, float sX, float sY) {
		Xcord = x;
		Ycord = y;
		SizeX = sX;
		SizeY = sY;
		// System.out.println("TILES MADE");
	}

	public float getXcord() {
		return Xcord;
	}

	public float getYcord() {
		return Ycord;
	}

	public float getSZX() {
		return SizeX;
	}

	public float getSZY() {
		return SizeY;
	}

	public void setXcord(float i) {
		Xcord = i;
	}

	public void setYcord(float i) {
		Ycord = i;
	}

	public void setSZX(float i) {
		SizeX = i;
	}

	public void setSZY(float i) {
		SizeY = i;
	}

	public void render(ShapeRenderer sr) {
		sr.rect(Xcord, Ycord, SizeX, SizeY);

	}
}
