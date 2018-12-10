package game2;

public class CordSet {
	private int X;
	private int Y;
	private boolean visit;
	public CordSet(int x, int y) {
		X = x;
		Y = y;
		visit = false;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	public boolean getVisit() {
		return visit;
	}

	public void setX(int i) {
		X = i;
	}

	public void setY(int i) {
		Y = i;
	}
	public void setVisit(boolean c) {
		visit = c;
	}

}
