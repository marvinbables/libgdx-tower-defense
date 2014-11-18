package gamedev.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Enemy {
	private String name;
	private int health, moneyReward;
	private float speed;
	List<Point> waypoints;
	
	public Enemy(String name, int health, int moneyReward, float speed) {
		this.health = health;
		this.moneyReward = moneyReward;
		this.speed = speed;
		waypoints = new ArrayList<Point>();
	}

	public int getHealth() {
		return health;
	}

	public int getMoneyReward() {
		return moneyReward;
	}

	public float getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}

	public List<Point> getWaypoints() {
		return waypoints;
	}
	
	public void addWaypoint(Point point) {
		waypoints.add(point);
	}
	
}
