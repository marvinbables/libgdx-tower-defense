package gamedev.entity;

import gamedev.entity.enemy.Skeleton;
import gamedev.entity.enemy.Spider;
import gamedev.td.Config;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;
import gamedev.td.helper.MathHelper;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity {
	protected enum Dir {
		LEFT, RIGHT, UP, DOWN
	}

	public enum EnemyType {
		Spider, Skeleton
	}

	protected float angle;
	private int health;
	private int moneyReward;
	protected float speed;
	protected float slowedSpeed;
	protected List<Point> waypoints;
	protected Dir dir;
	protected float slowAilmentTimer = 0;

	// enemy factory pattern
	/**
	 * The enemy factory pattern allows you to create the enemy
	 * @param type
	 * @param waypoints
	 * @return
	 */
	public static Enemy createEnemy(EnemyType type) {
		Enemy enemy = null;
		
		GameState state = GameState.getInstance();
		List<Point> waypointList = state.getWaypoints();

		SpriteManager handler = SpriteManager.getInstance();
		GDSprite sprite = handler.getEnemy(type);

		int health;
		int moneyReward;
		float speed;

		switch (type) {		
		
		
		case Spider:
			health = 20;
			moneyReward = 5;
			speed = 1.5f;
			enemy = new Spider(sprite, health, moneyReward, speed, waypointList);
			return enemy;
			
		case Skeleton:
			health = 50;
			moneyReward = 8;
			speed = 1;
			enemy = new Skeleton(sprite, health, moneyReward, speed, waypointList);
			return enemy;
			
		default:
			return enemy;
		}

	}

	protected Enemy(GDSprite sprite, int health, int moneyReward, float speed, List<Point> waypointList) {
		super(sprite);
		this.active = true;
		this.angle = 0;
		this.health = health;
		this.moneyReward = moneyReward;
		this.speed = speed;
		this.slowedSpeed = speed * .6f;
		this.position = Vector2.Zero;
		this.waypoints = waypointList;
		setPosition(MathHelper.PointToVector2(waypointList.get(0)));
		
	}

	public void update(float delta) {
		super.update(delta);
		checkIfReachedThePlayer(delta);
		float actualSpeed = getSpeed(delta);

		if (!waypoints.isEmpty()) {
			Point waypoint = waypoints.get(0);

			if (position.x > waypoint.x)
				dir = Dir.LEFT;
			else if (position.x < waypoint.x)
				dir = Dir.RIGHT;
			else if (position.y > waypoint.y)
				dir = Dir.UP;
			else if (position.y < waypoint.y)
				dir = Dir.DOWN;

			if (dir == Dir.LEFT) {
				angle = 180;
				position.x -= actualSpeed;
				if (position.x <= waypoint.x)
					position.x = waypoint.x;
			} else if (dir == Dir.RIGHT) {
				angle = 0;
				position.x += actualSpeed;
				if (position.x >= waypoint.x)
					position.x = waypoint.x;
			} else if (dir == Dir.UP) {
				angle = 270;
				position.y -= actualSpeed;
				if (position.y <= waypoint.y)
					position.y = waypoint.y;
			} else if (dir == Dir.DOWN) {
				angle = 90;
				position.y += actualSpeed;
				if (position.y >= waypoint.y)
					position.y = waypoint.y;
			}

			if (position.x == waypoint.x && position.y == waypoint.y) {
				waypoints.remove(0);
			}
		}
	}

	/**
	 * This method runs when the enemy has reached the player.
	 * 
	 * @param delta
	 */
	protected void checkIfReachedThePlayer(float delta) {
		if (waypoints.size() == 0 && isActive()) {
			setActive(false);
			GameState.getInstance().getDamaged();
		}
	}

	public void draw(SpriteBatch spriteBatch) {
		if (active) {
			sprite.setX(this.position.x);
			sprite.setY(this.position.y);
			sprite.setRotation(this.angle);
			if(slowAilmentTimer > 0){
				sprite.setColor(Config.green);
			}
			else{
				sprite.setColor(Config.normal);
			}
			sprite.draw(spriteBatch);
		}
	}
	
	protected float getSpeed(float delta){
		if(slowAilmentTimer > 0){
			slowAilmentTimer -= delta;
			return slowedSpeed;
		}
		else
			return speed;
	}
	
	public void slowedBySource(float time){
		slowAilmentTimer = time;
	}

	public void damagedBySource(int damage){
		health -= damage;
		if(health <= 0){
			this.active = false;
			GameState state = GameState.getInstance();
			state.addMoney(this.moneyReward);
		}
	}
	
	public static EnemyType interpretType(int type){
		switch(type - 1){
			case 0:
				return EnemyType.Spider;
			case 1:
				return EnemyType.Skeleton;
			default:
				return EnemyType.Spider;
		}
	}
	

	public int getMoneyReward() {
		return moneyReward;
	}


	public List<Point> getWaypoints() {
		return waypoints;
	}

	public void addPath(List<Point> points) {
		waypoints = points;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public float getAngle() {
		return this.angle;
	}
	
}
