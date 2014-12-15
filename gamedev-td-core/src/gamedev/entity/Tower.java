package gamedev.entity;

import gamedev.entity.Projectile.ProjectileType;
import gamedev.td.Config;
import gamedev.td.GDSprite;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower extends Entity {
	protected int damage, cost, upgradeCost, sellCost, level;
	protected float attackRange, attackRate, attackTimer, attackCooldown;
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
		this.position = Vector2.Zero;
		attackTimer = 0;
		setAttackCooldown(attackRate);
		targets = new ArrayList<Enemy>();
		center = new Point2D.Float();
		upgradeCost = 0;
		sellCost = 0;
	}

	public void draw(SpriteBatch spriteBatch) {
		if(active){
			sprite.setX(position.x);
			sprite.setY(position.y);
			sprite.draw(spriteBatch);
		}
	}

	public void update(float delta) {
		super.update(delta);
		List<Enemy> enemies = GameState.getInstance().getEnemies();
		acquireTarget(enemies);
		updateTargets();
		shoot(delta);
		
	}

	private void acquireTarget(List<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (!targets.contains(enemy)) {
				if (intersects(enemy)) {
					targets.add(enemy);
				}
			}
		}
	}

	private void updateTargets() {

		for (int i = targets.size() - 1; i >= 0; i--) {
			if (!intersects(targets.get(i)) || !targets.get(i).active) {
				targets.remove(i);
			}

		}
	}

	private void shoot(float delta) {
		attackTimer += delta;
		if(targets.size() > 0){
			List<Projectile> projectiles = GameState.getInstance().getProjectiles();
			if(attackTimer >= attackCooldown){
				attackTimer = 0;
				ProjectileType type = Projectile.interpretTypeFromTowerName(towerName);
				Projectile shotProjectile = Projectile.createProjectile(this, type, targets.get(0));
				projectiles.add(shotProjectile);
			}
		}
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
	
	protected void setAttackCooldown(float attackRate){
		attackCooldown = 1f / attackRate;
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
	
	public Tower clone() {
		Tower t = TowerFactory.createTower(TowerFactory.interpretType(towerName));
		t.active = true;
		return t; 
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

	
}
