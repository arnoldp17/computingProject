package game2;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Entity {
	private float Xcord;
	private float Ycord;
	private float Size;
	private float Speed;
	private float absolSpeed;

	public Entity(float X, float Y, float Spd, float SZ) {
		Xcord = X;
		Ycord = Y;
		absolSpeed = Spd;
		Size = SZ;
		Speed = absolSpeed;
	}

	public void renderEntity(ShapeRenderer sr) {
		sr.setColor(Color.FIREBRICK);
		sr.circle(Xcord, Ycord, Size);
	}

	public float getSpd() {
		return absolSpeed;
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

	public void setSpd(float i) {
		Speed = i;
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

	public void MoveX() {
		Xcord += Speed;
	}

	public void MoveY() {
		Ycord += Speed;
	}

	public void stop() {
		Speed = 0;
	}

	public void travel(Walls blocks, int noOfFiles, char dir) {

		if (dir == 'A') {

			if (canMoveLR(blocks, noOfFiles, Speed) == true) {

				MoveX();

			}

		}

		if (dir == 'D') {
			if (canMoveLR(blocks, noOfFiles, Speed) == true) {

				MoveX();

			}

		}

		if (dir == 'S') {

			if (canMoveUD(blocks, noOfFiles, Speed) == true) {

				MoveY();

			}
		}

		if (dir == 'W')

		{
		}
		if (canMoveUD(blocks, noOfFiles, Speed) == true) {

			MoveY();

		}
	}

	private boolean canMoveUD(Walls blocks, int noOfFiles, float speed) {

		float testY = Ycord + speed;
		int issue = 0;

		if (boxCollide(blocks, noOfFiles, Xcord, testY) == true) {
			issue++;
		}

		if (CheckWall(Xcord, testY) == true) {
			issue++;
		}

		if (issue == 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean canMoveLR(Walls blocks, int noOfFiles, float speed) {

		float testX = Xcord + speed;
		int issue = 0;

		if (boxCollide(blocks, noOfFiles, testX, Ycord) == true) {
			issue++;
		}

		if (CheckWall(testX, Ycord) == true) {
			issue++;
		}

		if (issue == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckWall(float px, float py) {
		int check = 0;

		if ((px - Size) < 1) {
			check++;
		}

		if (Gdx.app.getGraphics().getWidth() < (px + Size)) {
			check++;
		}

		if ((py - Size) < 1) {
			check++;
		}

		if (Gdx.app.getGraphics().getHeight() < (py + Size)) {
			check++;
		}

		if (check != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean boxCollide(Walls blocks, int noOfFiles, float px, float py) {
		for (int k = 0; k < blocks.size(); k++) {
			// loops over every tile in every block
			float X2 = blocks.getTileDataX(k);
			float Y2 = blocks.getTileDataY(k);
			float SX2 = blocks.getTileDataSZX(k);
			float SY2 = blocks.getTileDataSZY(k);
			// gets width and height

			float lftSd = px - Size;
			float rghtSd = px + Size;
			// gets left and right sides
			float ovr = py - Size;
			float undr = py + Size;
			// gets up and down
			if (lftSd <= X2 + SX2 && rghtSd >= X2) {
				if (Y2 + SY2 >= ovr && undr >= Y2) {
					// collide = true;
					return true;
					// if touching object collide = true
				}

			}
		}

		return false;

	}
}
