package gamedev.entity;

import gamedev.entity.enemy.Spider;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnemyFactory {
	
	public static Enemy makeEnemy(int enemyType, Point [] waypoints){
		Enemy enemy = null;		
		int health, moneyReward;
		float speed;
		List<Point> waypointList = new ArrayList<Point>(Arrays.asList(waypoints));
		
		if(enemyType == 1){
			health = 50;
			moneyReward = 10;
			speed = 1.5f;
			enemy = new Spider(health, moneyReward, speed);
			
			/*
			 * "From the API:
			 * 		Arrays.asList: Returns a fixed-size list backed by the specified array.
			 *  You can't add to it; you can't remove from it. You can't structurally modify the List."
			 *  --> http://stackoverflow.com/questions/2965747/why-i-get-unsupportedoperationexception-when-trying-to-remove-from-the-list
			 */
			// Throws UnsupportedOperationException when Arrays.asList(waypoints) is used as it is 
			// (must pass it to a constructor of a List eg. ArrayList or LinkedList)
			enemy.addWaypoint(waypointList);
			enemy.setX(waypoints[0].x);
			enemy.setY(waypoints[0].y);
		}
		else if (enemyType == 2){
			//TODO skeleton stats
			
		}
			
		
		return enemy;
		
	}

}
