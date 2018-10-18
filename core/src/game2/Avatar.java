package game2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Avatar {
	private float speedX;
	private float speedY;
	private float Xcord;
	private float Ycord;
	private float Size;
	private char KorM;
	private int health;
	private int armour;

	public Avatar(float X, float Y, float SX, float SY, float SZ, char KM) {
		Xcord = X;
		Ycord = Y;
		speedX = SX;
		speedY = SY;
		Size = SZ;
		KorM = KM;
		if (KorM == 'M') {
			health = 4;
			armour = 5;
		} else {
			health = 5;
			armour = 7;
		}
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

	public float getHlth() {
		return health;
	}

	public void setHlth(int i) {
		health = i;
	}

	public float getAmr() {
		return armour;
	}

	public void setAmr(int i) {
		armour = i;
	}

	public void MoveX() {
		Xcord += speedX;
	}

	public void MoveY() {
		Ycord += speedY;
	}

	public void stop() {
		speedX = 0;
		speedY = 0;
	}

	public void Motion() {
//float X2, float Y2, float S2
		if (Gdx.input.isKeyPressed(Keys.A)) {

			if (CheckWall(Xcord, Ycord, Size) != true) {

				if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.S)) {
					setSpdX(-2);
				} else {
					setSpdX(-4);
				}
				MoveX();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			if (CheckWall(Xcord, Ycord, Size) != true) {

				if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.S)) {
					setSpdX(2);
				} else {
					setSpdX(4);
				}
				MoveX();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			if (CheckWall(Xcord, Ycord, Size) != true) {

				if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D)) {
					setSpdY(-2);
				} else {
					setSpdY(-4);
				}
				MoveY();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			if (CheckWall(Xcord, Ycord, Size) != true) {

				if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D)) {
					setSpdY(2);
				} else {
					setSpdY(4);
				}
				MoveY();
			}
		}

	}

	public boolean CheckWall(float px, float py, float s) {
		int check = 0;
		if ((px - s) < 1) {
			speedX = 0;
			Xcord = px + 4;
			check++;
		}
		if (Gdx.app.getGraphics().getWidth() < (px + s)) {
			speedX = 0;
			Xcord = px - 5;
			check++;
		}
		if ((py - s) < 1) {
			py = py + 5;
			Ycord = py + 4;
			speedY = 0;
			check++;
		}
		if (Gdx.app.getGraphics().getHeight() < (py + s)) {
			speedY = 0;
			Ycord = py - 4;
			check++;
		}
		if (check != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean boxCollide(float X2, float Y2, float S2) {
		boolean collide = false; // TURN INTO A FUCNTION THAT CHECKS FOR EACH DIRECTION
		float lftSd = Xcord - Size;
		float rghtSd = Xcord + Size;
		float ovr = Ycord - Size;
		float undr = Ycord + Size;
		if (lftSd <= X2 + S2 && rghtSd >= X2) {
			if (Y2 + S2 >= ovr && undr >= Y2) {
				collide = true;

			}

		}

		return collide;

	}

	public void renderAvatar(ShapeRenderer sr) {
		sr.setColor(Color.BLUE);
		sr.circle(Xcord, Ycord, Size);

	}
}
