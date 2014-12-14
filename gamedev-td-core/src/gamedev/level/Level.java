package gamedev.level;

import java.util.ArrayList;
import java.util.List;


public class Level {
	
	private static Level instance;

	private int[][] enemies;

	private Level(int[][] enemies){

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

			int[][] enemies = {
				// instances, enemy type
				{10, 1}, {1,1}
			};

			level = new Level(enemies);
			break;
			
		case 2:
			int[][] enemies2 = {
				// instances, enemy type
				{10, 1}, {1,1}
			};

			level = new Level(enemies2);
			break;
		}
		return level;
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
