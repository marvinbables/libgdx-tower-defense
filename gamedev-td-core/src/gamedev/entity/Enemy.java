package gamedev.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Enemy {
	private int x, y;
	private String name;
	private int health, moneyReward;
	private float speed;
	private List<Point> waypoints;
	
	public Enemy(String name, int health, int moneyReward, float speed) {
		this.name = name;
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
	
	public void addWaypoint(List<Point> points) {
		waypoints = points;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
