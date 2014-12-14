package gamedev.entity;

import gamedev.td.Config;
import gamedev.td.GDSprite;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tower extends Entity {
	protected int damage, x, y, cost, upgradeCost, sellCost, level;
	protected float attackRange, attackRate, attackTimer;
	protected Point2D.Float center;
	protected String towerName;

	private ArrayList<Enemy> targets = null;

	public Tower(GDSprite sprite, int damage, float attackRange,
			float attackRate, int cost, int level, String towerName) {
		super(sprite);
		this.damage = damage;
		this.attackRange = attackRange;
		this.attackRate = attackRate;
		this.cost = cost;
		this.towerName = towerName;
		this.level = level;
		x = -500;
		y = -500;
		attackTimer = 0;
		targets = new ArrayList<Enemy>();
		center = new Point2D.Float();

		upgradeCost = 0;
		sellCost = 0;
	}

	public void draw(SpriteBatch spriteBatch) {
		sprite.setX(this.x);
		sprite.setY(this.y);
		sprite.draw(spriteBatch);

	}

	public void update(float delta) {
		List<Enemy> enemies = GameState.getInstance().getEnemies();
		acquireTarget(enemies);
		updateTargets();
	}

	public void acquireTarget(List<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (!targets.contains(enemy)) {
				if (intersects(enemy)) {
					targets.add(enemy);
				}
			}
		}
	}

	public void updateTargets() {

		for (int i = targets.size() - 1; i >= 0; i--) {
			if (!intersects(targets.get(i))) {
				targets.remove(i);
			}

		}
	}

	public void shoot() {

	}

	public int getDamage() {
		return damage;
	}

	public float getAttackRange() {
		return attackRange;
	}

	public float getAttackRate() {
		return attackRate;
	}

	public float getAttackTimer() {
		return attackTimer;
	}

	public String getTowerName() {
		return towerName;
	}

	public ArrayList<Enemy> getTarget() {
		return targets;
	}

	public void setTarget(ArrayList<Enemy> targets) {
		this.targets = targets;
	}

	public abstract void upgrade();

	/*
	 * TODO: Increment the level of the tower Use the level of the tower as the index in the array of upgrade/sell costs Set the upgradeCost to
	 * upgradeArray[level] Set the sellCost to sellArray[level]
	 */

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public int getSellCost() {
		return sellCost;
	}

	public int getLevel() {
		return level;
	}

	public boolean intersects(Enemy enemy) {
		float circleDistanceX = (float) Math.abs(center.getX() - enemy.getPosition().x);
		float circleDistanceY = (float) Math.abs(center.getY() - enemy.getPosition().y);

		if (circleDistanceX > Config.tileSize / 2 + attackRange) {
			return false;
		}

		if (circleDistanceY > Config.tileSize / 2 + attackRange) {
			return false;
		}

		if (circleDistanceX <= Config.tileSize / 2) {
			return true;
		}

		if (circleDistanceY <= Config.tileSize / 2) {
			return true;
		}

		float cornerDistance = (circleDistanceX - Config.tileSize / 2) * (circleDistanceX - Config.tileSize / 2) + (circleDistanceY - Config.tileSize / 2) * (circleDistanceY - Config.tileSize / 2);

		return (cornerDistance <= attackRange * attackRange);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setCenter(float x, float y) {
		center.setLocation(x, y);
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
