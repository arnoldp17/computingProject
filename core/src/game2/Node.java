package game2;

public class Node {
	private float Xcord;
	private float Ycord;
	private float Cost;
	private boolean visited;

	public Node(float x, float y, float c, boolean v) {
		Xcord = x;
		Ycord = y;
		Cost = c;
		visited = v;
	}

	public float getX() {
		return Xcord;
	}

	public float getY() {
		return Ycord;
	}

	public float getCost() {
		return Cost;
	}
	
	public boolean getVisit() {
		return visited;
	}
	
	public void setVisit( boolean v) {
		visited = v;
	}

	public void setX(int i) {
		Xcord = i;
	}

	public void setY(int i) {
		Ycord = i;
	}

	public void setCost(int i) {
		Cost = i;
	}
}
