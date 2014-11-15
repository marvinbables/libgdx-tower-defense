package gamedev.entity;


public class Tower {
	private int damage;
	private float attackRange, attackRate,
		attackTimer;
	
	public Tower(int damage, float attackRange, float attackRate,
			float attackTimer) {
		this.damage = damage;
		this.attackRange = attackRange;
		this.attackRate = attackRate;
		this.attackTimer = attackTimer;
	}
	
}
