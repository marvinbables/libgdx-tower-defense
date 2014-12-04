package gamedev.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class Tower {
	private int damage, x, y, cost;
	private float attackRange, attackRate,
		attackTimer;
	private Point2D.Float center;
	
	private ArrayList<Enemy> targets = null;
	
	public Tower(int damage, float attackRange, float attackRate, int cost) {
		this.damage = damage;
		this.attackRange = attackRange;
		this.attackRate = attackRate;
		this.cost = cost;
		x = -50;
		y = -50;
		attackTimer = 0;
		targets = new ArrayList<Enemy>();
		center = new Point2D.Float();
	}
	
	public void acquireTarget(List<Enemy> enemies) {
		float tempX;
		float tempY;
		for(Enemy enemy : enemies){
			if(!targets.contains(enemy)){
				tempX = (float) (enemy.getX() - center.getX());
				tempY = (float) (enemy.getY() - center.getY());
				if(tempX * tempX + tempY * tempY < attackRange * attackRange){
					targets.add(enemy);
				}
			}
		}
	}
	
	public void shoot() {
		/*
		 * http://gamedev.stackexchange.com/questions/14469/2d-tower-defense-a-bullet-to-an-enemy
		 * http://blog.publysher.nl/2012/05/stencyl-tower-defense-4-shooting.html
		 */
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Enemy> getTarget() {
		return targets;
	}

	public void setTarget(ArrayList<Enemy> targets) {
		this.targets = targets;
	}
	
	public void updateTargets(){
		float tempX;
		float tempY;
		for(int i = targets.size() - 1; i >= 0; i--){
			tempX = (float) (targets.get(i).getX() - center.getX());
			tempY = (float) (targets.get(i).getY() - center.getY());
			if(tempX * tempX + tempY * tempY >= attackRange * attackRange){
				targets.remove(i);
			}
				
		}
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setCenter(float x, float y){
		center.setLocation(x, y);
	}
	
}
