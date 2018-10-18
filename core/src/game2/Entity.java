package game2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Entity {
	private float speedX;
	private float speedY;
	private float Xcord;
	private float Ycord;
	private float Size;

	public Entity(float X, float Y, float SX, float SY, float SZ) {
		Xcord = X;
		Ycord = Y;
		speedX = SX;
		speedY = SY;
		Size = SZ;

	}

	public void renderEntity(ShapeRenderer sr) {
		sr.setColor(Color.FIREBRICK);
		sr.circle(Xcord, Ycord, Size);
	}

	public float getSpdX() {
		return speedX;
	}

	public float getSpdY() {
		return speedY;
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

	public void setSpdX(float i) {
		speedX = i;
	}

	public void setSpdY(float i) {
		speedY = i;
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
		Xcord += speedX;
	}

	public void stop() {
		speedX = 0;
		speedY = 0;
	}

	public void MoveY() {
		Ycord += speedY;
	}

	public void CheckWall(float px, float py, float s) {
		if ((px - s) < 1) {
			speedX = speedX * -1;
		}
		if (Gdx.app.getGraphics().getWidth() < (px + s)) {
			speedX = speedX * -1;
		}
		if ((py - s) < 1) {
			speedY = speedY * -1;
		}
		if (Gdx.app.getGraphics().getHeight() < (py + s)) {
			speedY = speedY * -1;
		}
	}

	public void travel() {
		MoveY();
		MoveX();
		CheckWall(Xcord, Ycord, Size);
	}
}

