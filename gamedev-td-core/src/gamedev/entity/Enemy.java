package gamedev.entity;

import gamedev.entity.enemy.Spider;
import gamedev.td.GDSprite;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public abstract class Enemy extends Entity{
	enum Dir {
		LEFT, RIGHT, UP, DOWN
	}
	
	private float angle;
	private int health;
	private int moneyReward;
	private float speed;
	private List<Point> waypoints;
	private Dir dir;
	
	public Enemy(GDSprite sprite, int health, int moneyReward, float speed) {
		super(sprite);
		this.active = true;
		this.angle = 0;
		this.health = health;
		this.moneyReward = moneyReward;
		this.speed = speed;
		this.waypoints = new ArrayList<Point>();
		this.position = Vector2.Zero;
	}
	
	public void update(float delta) {
		// Move enemy sprite
		if (isActive()){
			setPosition(position);
			GameState
		}
		
		if(!waypoints.isEmpty()) {
			Point waypoint = waypoints.get(0);
			
			if(position.x > waypoint.x)
				dir = Dir.LEFT;
			else if(position.x < waypoint.x)
				dir = Dir.RIGHT;
			else if(position.y > waypoint.y)
				dir = Dir.UP;
			else if(position.y < waypoint.y)
				dir = Dir.DOWN;
			
			if(dir == Dir.LEFT) {
				angle = 180;
				position.x -= speed;
				if(position.x <= waypoint.x)
					position.x = waypoint.x;
			}
			else if(dir == Dir.RIGHT) {
				angle = 0;
				position.x += speed;
				if(position.x >= waypoint.x)
					position.x = waypoint.x;
			}
			else if(dir == Dir.UP) {
				angle = 270;
				position.y -= speed;
				if(position.y <= waypoint.y)
					position.y = waypoint.y;
			}
			else if(dir == Dir.DOWN) {
				angle = 90;
				position.y += speed;
				if(position.y >= waypoint.y)
					position.y = waypoint.y;
			}
			
			if(position.x == waypoint.x && position.y == waypoint.y) {
				waypoints.remove(0);
			}
		}
	}
	
	
	public void draw(SpriteBatch spriteBatch){
		if(active){
			sprite.setX(this.position.x);
			sprite.setY(this.position.y);
			sprite.setRotation(this.angle);
			sprite.draw(spriteBatch);
		}
	}
	
	//enemy factory pattern
	public static Enemy createEnemy(int type, Point [] waypoints){
		Enemy enemy = null;
		int health, moneyReward;
		float speed;
		List<Point> waypointList = Arrays.asList(waypoints);
		
		switch(type){
		
			case 1:		//spider
				health = 50;
				moneyReward = 10;
				speed = 1.5f;
				enemy = new Spider(health, moneyReward, speed);
				enemy.addWaypoint(waypointList);
				//set sprite here
				return enemy;
			
			default:
				return enemy;
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

	public List<Point> getWaypoints() {
		return waypoints;
	}
	
	public void addWaypoint(List<Point> points) {
		waypoints = points;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public float getAngle(){
		return this.angle;
	}	
}
