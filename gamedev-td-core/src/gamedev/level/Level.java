package gamedev.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Level {
	
	private static Level instance;
	
	private String[] grid;
	private Point[] waypoints;
	private int[][] enemies;

	private Level(String[] grid, Point[] waypoints, int[][] enemies){
		this.grid = grid;
		this.waypoints = waypoints;
		this.enemies = enemies;
	}
	
	/**
	 * Template pattern. This code allows you to generate various templates on a switch case.
	 * You can improve this by saving the templates into a file and parsing it, so that your database can be edited outside your code.
	 * @param i
	 * @return
	 */
	public static Level generateLevel(int i) {
		Level level = null;
		
		switch(i){
		case 1: 
			
			String grid[] = {"00000000000000000",
						  "11111110000000000",
						  "00000010000000000",
						  "00000010000000000",
						  "00000010000022220",
						  "00000010000020020",
						  "00000010001110320",
						  "00000010001020020",
						  "00000010001022220",
						  "00000011111000000",
						  "00000000000000000",
						  "00000000000000000",};
			Point waypoints[] = {	// x, y
				new Point(0, 40),
				new Point(240, 40),
				new Point(240, 360),
				new Point(400, 360),
				new Point(400, 240),
				new Point(480, 240)
				};
			

			int[][] enemies = {
				// instances, enemy type
				{10, 1}, {1,1}
			};

			level = new Level(grid, waypoints, enemies);
			break;
		}
		return level;
	}

	public String[] getGrid() {
		return grid;
	}

	public Point[] getWaypoints() {
		return waypoints;
	}

	public int[][] getEnemies() {
		return enemies;
	}

	public static Level getInstance() {
		if (instance == null)
			instance = generateLevel(1);
		return instance;
	}

	/**
	 * TODO: This can be made more readable.
	 * @return
	 */
	public List<Integer> getEnemiesToBeSpawned() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < enemies.length; i++) {
			for (int j = 0; j < enemies[i][0]; j++)
				list.add(enemies[i][1]);
		}
		return list;
	}
}
