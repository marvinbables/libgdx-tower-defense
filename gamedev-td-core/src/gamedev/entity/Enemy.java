package gamedev.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Enemy {
	enum Dir {
		LEFT, RIGHT, UP, DOWN
	}
	
	private int x, y;
	private String name;
	private int health, moneyReward;
	private float speed;
	private List<Point> waypoints;
	private Dir dir = Dir.RIGHT;
	
	public Enemy(String name, int health, int moneyReward, float speed) {
		this.name = name;
		this.health = health;
		this.moneyReward = moneyReward;
		this.speed = speed;
		waypoints = new ArrayList<Point>();
		x = -50;
		y = -50;
	}
	
	public void move() {
		if(!waypoints.isEmpty()) {
			Point waypoint = waypoints.get(0);
			
			if(x > waypoint.x)
				dir = Dir.LEFT;
			else if(x < waypoint.x)
				dir = Dir.RIGHT;
			else if(y > waypoint.y)
				dir = Dir.UP;
			else if(y < waypoint.y)
				dir = Dir.DOWN;
			
			if(dir == Dir.LEFT) {
				x -= speed;
				if(x <= waypoint.x)
					x = waypoint.x;
			}
			else if(dir == Dir.RIGHT) {
				x += speed;
				if(x >= waypoint.x)
					x = waypoint.x;
			}
			else if(dir == Dir.UP) {
				y -= speed;
				if(y <= waypoint.y)
					y = waypoint.y;
			}
			else if(dir == Dir.DOWN) {
				y += speed;
				if(y >= waypoint.y)
					y = waypoint.y;
			}
			
			if(x == waypoint.x && y == waypoint.y) {
				waypoints.remove(0);
			}
		}
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

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
}
