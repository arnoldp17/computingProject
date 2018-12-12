package game2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game2.Avatar;
import game2.Entity;
import game2.Walls;

public class Level {
	private Avatar player = new Avatar(450, 450, 5, 7, 'M'); // defines the player and its attributes
	private ArrayList<Entity> foes = new ArrayList<Entity>(); // creates a list for enemies
	private int difficulty = 1; // Sets the difficulty multiplier
	private int maxFileNo = 3; // sets the max number of tiles per block
	private Walls block;
	private int Xsize;
	private int Ysize;
	private Graph graph;
	float countDown = (float) 1.5;

	public Level(int difMult, int bMax, int X, int Y) {
		// difficulty = difMult * difficulty; // will be used as the maximum value of
		// enemies per rooms
		Xsize = X;
		Ysize = Y;
		createLevel();

	}

	public void createLevel() {

		block = new Walls(maxFileNo, 20, 50, Xsize, Ysize);
		graph = new Graph(Xsize, Ysize, 10, block);
		populate();
	}

	public void renderLvl(ShapeRenderer sr) {

		player.Motion(block, maxFileNo);
		player.renderAvatar(sr);
		// System.out.println("rendered block");
		for (int j = 0; j < block.size(); j++) {
			// System.out.println("In loop");
			block.renderBlock(sr);
			// System.out.println("rendered blocks No " + j);
		}
		for (int i = 0; i < foes.size(); i++) {
			// System.out.println("In entity render");
			foes.get(i).renderEntity(sr);
			// System.out.println("rendered entity No " + i);
		}

		// System.out.println(Gdx.graphics.getDeltaTime());
		if (countDown >= 1) {
			countDown = countDown - (Gdx.graphics.getDeltaTime());
			 //System.out.println(countDown);
		} else {
			for (int i = 0; i < foes.size(); i++) {
				Node start = new Node(Math.round(foes.get(i).getXcord()), Math.round(foes.get(i).getYcord()), 0, false);
				Node end = new Node(Math.round(player.getXcord()), Math.round(player.getYcord()), 0, false);
				System.out.println("PATH FIND!");
				// ArrayList<Node> Route = pathfindAstr(start, end);
				//ArrayList<Node> route = pathfind(start, end, block);
				/*ArrayList<Node> path = getPath(route, start, end);
				System.out.println("PATH FOUND");
				System.out.println("Path is as follows:");
				for (int j = 0; j < path.size(); j++) {
					System.out.println("(" + path.get(j).getX() + "," + path.get(j).getY() + ")");
				} */
				countDown = 5;

			}
		}

	}

	public ArrayList<Node> pathfind(Node root, Node goal, Walls block) {
		Queue<Node> frontier = new LinkedList<Node>();
		
		
		
		
		
		
		return null;

}

	private ArrayList<Node> getPath(ArrayList<Node> closedSet, Node root, Node goal) {
		ArrayList<Node> path = new ArrayList<Node>();
		int i = 0;
		Node current = path.get(i);

		while (cordMatch(current, root) != true) {
			i++;
			path.add(current);
			current = closedSet.get(i);
		}
		return path;
	}

	public boolean cordMatch(Node subtreeRoot, Node goal) {
		if (subtreeRoot.getX() == goal.getX()) {
			if (subtreeRoot.getY() == goal.getY()) {
				return true;
			}
		}

		return false;

	}

	public ArrayList<Node> pathfindAstr(Node start, Node end) {
/*		System.out.println("Pathfinding begins");
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		
		frontier.add(start);
		System.out.println("Start added");
		ArrayList<Node> cameFrom = new ArrayList<Node>();
		ArrayList<Integer> costSoFar = new ArrayList<Integer>();
		costSoFar.add(1);

		System.out.println("Enter loop");
		while (frontier.isEmpty() == false) {
			System.out.println("In pathfind loop");
			int nodeChecked = 0;
			Node check = frontier.poll();
			if (check != end) {
				System.out.println("Endnode not current");
				Node current = new Node(check.getX(), check.getY(), check.getCost(), check.getVisit());
				ArrayList<Node> neighbours = graph.getNeighbour(current, block);
				System.out.println("Neighbour found");

				for (int i = 0; i < 4; i++) {
					System.out.println("Coost loop");
					int newCost = (int) (costSoFar.get(nodeChecked) + neighbours.get(i).getCost());
					System.out.println("New cost defined");
					for (int j = 0; j < costSoFar.size(); j++) {
						System.out.println("Cost checker");
						if (newCost < costSoFar.get(nodeChecked) || costSoFar.get(j) != costSoFar.get(nodeChecked)) {
							System.out.println("Correct direction choice");
							costSoFar.add(newCost);
							float priority = newCost + heuristic(end, neighbours.get(i));
							Node next = new Node(neighbours.get(i).getX(), neighbours.get(i).getY(), priority, neighbours.get(i).getVisit());
							frontier.add(next);
							cameFrom.add(current);
						}
					}
				}

			}
			nodeChecked++;
			System.out.println(nodeChecked + " Node checked");
		}*/
		return null ;//cameFrom
	}

	public Float heuristic(Node a, Node b) {
		return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));
	}

	public void populate() {
		// difficulty = difficulty * 10;
		for (int f = 0; f < difficulty; f++) {
			Entity enemy = new Entity((f * 50) + 400, 400, (float) (1), 7);
			foes.add(enemy);
		}
	}

	public static int getRNDI(int i) {
		int rnd = new Random().nextInt(i);
		return rnd;
	}

}
