package gamedev.entity;

import gamedev.screen.GameScreen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class EnemyFactory {
	
	public static Enemy makeEnemy(int enemyType, Point [] waypoints){
		Enemy enemy = null;		
		int health, moneyReward;
		float speed;
		
		if(enemyType == 1){
			health = 50;
			moneyReward = 10;
			speed = 1.5f;
			enemy = new Enemy("spider", health, moneyReward, speed);
			for (int i = 0; i < waypoints.length; i++) {
				waypoints[i].x *= GameScreen.tileSize;
				waypoints[i].y *= GameScreen.tileSize;
			}
			// Throws UnsupportedOperationException when Arrays.asList(waypoints) is used as it is 
			// (must pass it to a constructor of a List eg. ArrayList or LinkedList)
			enemy.addWaypoint(new ArrayList<Point>(Arrays.asList(waypoints))); 
			enemy.setX(waypoints[0].x);
			enemy.setY(waypoints[0].y);
		}
		else if (enemyType == 2){
			//TODO skeleton stats
			
		}
			
		
		return enemy;
		
	}

}
