package gamedev.entity;


public class Tower {
	private int damage, x, y, cost;
	private float attackRange, attackRate,
		attackTimer;
	
	private Enemy target = null;
	
	public Tower(int damage, float attackRange, float attackRate, int cost) {
		this.damage = damage;
		this.attackRange = attackRange;
		this.attackRate = attackRate;
		this.cost = cost;
		x = -50;
		y = -50;
		attackTimer = 0;
	}
	
	public void acquireTarget() {
		// TODO: Acquire target. If current target leaves the range, target the next enemy behind the previous target that left the tower range
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

	public Enemy getTarget() {
		return target;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
