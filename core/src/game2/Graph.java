package game2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Graph {
	private int mapSizeX;
	private int mapSizeY;
	private int chunkSize;
	private ArrayList<Node> nodes = new ArrayList<Node>(); // stores a value of chunks (10 by 10 grids) to simplify
															// things

	public Graph(int x, int y, int c, Walls blocks) {
		mapSizeX = x;
		mapSizeY = y;
		chunkSize = c;
		chunker();
	}

	public Node getNode(int i) {
		return nodes.get(i);

	}

	public Queue<Node> getNeighbour(Node i, Walls blocks) {
		System.out.println("Getting neighbours");
		Queue<Node> neighbours = new LinkedList<Node>();

		Node up = new Node(i.getX(), i.getY() + chunkSize, makeCost(i, blocks), i.getVisit());
		neighbours.add(up);
		Node rght = new Node(i.getX() + chunkSize, i.getY(), makeCost(i, blocks), i.getVisit());
		neighbours.add(rght);
		Node lft = new Node(i.getX() - chunkSize, i.getY(), makeCost(i, blocks), i.getVisit());
		neighbours.add(lft);
		Node dwn = new Node(i.getX(), i.getY() - chunkSize, makeCost(i, blocks), i.getVisit());
		neighbours.add(dwn);

		System.out.println("node (" + up.getX() + "," + up.getY() + ")" + " costs " + up.getCost());
		System.out.println("node (" + rght.getX() + "," + rght.getY() + ")" + " costs " + rght.getCost());
		System.out.println("node (" + lft.getX() + "," + lft.getY() + ")" + " costs " + lft.getCost());
		System.out.println("node (" + dwn.getX() + "," + dwn.getY() + ")" + " costs " + dwn.getCost());

		return neighbours;

	}

	private void chunker() {
		for (int i = 0; i < mapSizeX; i = i + chunkSize) {
			for (int j = 0; j < mapSizeY; j = j + chunkSize) {
				Node chunkCentre = new Node((i + (chunkSize / 2)), (j + (chunkSize / 2)), 1, false);
				nodes.add(chunkCentre);
				System.out.println((i + (chunkSize / 2)) + "|" + (j + (chunkSize / 2)));
			}
		}
	}

	public boolean boxCheck(Walls blocks, Node node) {
		for (int k = 0; k < blocks.size(); k++) {
			// loops over every tile in every block
			float X2 = blocks.getTileDataX(k);
			float Y2 = blocks.getTileDataY(k);
			float SX2 = blocks.getTileDataSZX(k);
			float SY2 = blocks.getTileDataSZY(k);
			// gets width and height

			float lftSd = node.getX() - chunkSize;
			float rghtSd = node.getX() + chunkSize;
			// gets left and right sides
			float ovr = node.getY() - chunkSize;
			float undr = node.getY() + chunkSize;
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

	public boolean getVisit(float x, float y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				return (nodes.get(i).getVisit());
			}
		}
		return false;
	}

	public void setVisit(boolean v, float x, float y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				nodes.get(i).setVisit(v);
			}
		}
	}

	public boolean findVisit(Node find) {
		for (int i = 0; i < nodes.size(); i++) {
			if (cordMatch(find, nodes.get(i)) == true) {
				return nodes.get(i).getVisit();
			}
		}
		return false;
	}

	public void setCost(int v, float x, float y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				nodes.get(i).setCost(v);
			}
		}
	}

	public int makeCost(Node check, Walls block) {
		if (boxCheck(block, check) == true) {
			System.out.println("Wall found");
			return Integer.MAX_VALUE;

		}
		return 1;
	}

	public float getCost(float x, float y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				return (nodes.get(i).getCost());
			}
		}
		return 1;
	}

	public void renderGraph(ShapeRenderer sr) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getCost() == 1) {
				sr.setColor(Color.GREEN);
				sr.rect(nodes.get(i).getX(), nodes.get(i).getY(), chunkSize, chunkSize);
			}
			if (nodes.get(i).getCost() == 2) {
				sr.setColor(Color.PURPLE);
				sr.rect(nodes.get(i).getX(), nodes.get(i).getY(), chunkSize, chunkSize);
			}
		}
	}

	public void renderNode(ShapeRenderer sr, float x, float y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				if (nodes.get(i).getCost() == 1) {
					sr.setColor(Color.GREEN);
					sr.rect(nodes.get(i).getX(), nodes.get(i).getY(), chunkSize, chunkSize);
				}
				if (nodes.get(i).getCost() == 2) {
					sr.setColor(Color.PURPLE);
					sr.rect(nodes.get(i).getX(), nodes.get(i).getY(), chunkSize, chunkSize);
				}
			}
		}
	}

	public boolean pathfind(Node start, Node end, ShapeRenderer sr, Walls block) {

		int checked = 0;
		Queue<Node> frontier = new LinkedList<Node>();
		ArrayList<Node> cameFrom = new ArrayList<Node>();
		cameFrom.add(start);
		frontier.add(start);

		while (frontier.isEmpty() == false) {

			//if (checked > 8 /* nodes.size() */) {
			//	System.exit(0);
			//}

			System.out.println("Pathfinding");
			Node current = frontier.poll();
			Queue<Node> neighbours = getNeighbour(current, block);

			if (cordMatch(current, end) == true) {
				return true;
			}

			System.out.println("Checking (" + current.getX() + ", " + current.getY() + ")");

			while (neighbours.isEmpty() == false) {

				System.out.println("In neighbour loop");
				Node checkMe = neighbours.poll();
				System.out.println("Checking neighbour " + neighbours.size());

				checked++;
				System.out.println("Checked " + checked);
				if (checked > 40 /* nodes.size() */) {
					//System.exit(0);
				}
				if (massCheck(checkMe, cameFrom) == false) {

					setCost(2, checkMe.getX(), checkMe.getY());
					renderNode(sr, checkMe.getX(), checkMe.getY());
					frontier.add(checkMe);
					if (massCheck(current, cameFrom) == false) {
						System.out.println("Adding (" + current.getX() + ", " + current.getY() + ")");
						cameFrom.add(current);
					}
				}

			}

		}
		return false;

	}

	public boolean cordMatch(Node check, Node goal) {
		if (check.getX() == goal.getX() && check.getY() == goal.getY()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MATCH FOUND AT (" + check.getX() + ", " + check.getY() + ")");
			return true;

		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("NO MATCH");
		return false;

	}

	public boolean massCheck(Node check, ArrayList<Node> CameFrom) {
		int matches = 0;
		for (int i = 0; i < CameFrom.size(); i++) {
			boolean match = cordMatch(check, CameFrom.get(i));
			if (match = true) {
				matches++;
			}
		}
		if (matches < 0) {
			return true;
		}
		return false;
	}
}
