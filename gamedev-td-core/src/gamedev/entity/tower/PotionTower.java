package gamedev.entity.tower;

import gamedev.entity.Tower;
import gamedev.td.GDSprite;

public class PotionTower extends Tower {

	private int maxLevel = 3;
	private static int damageLevels[] = {5};
	private static int rangeLevels[] = {100};
	private static float attackRateLevels[] = {0.9f};
	
	public PotionTower(GDSprite sprite, int level, int cost) {
		super(sprite, damageLevels[level], rangeLevels[level], attackRateLevels[level], cost, level, "Potion Tower");
		
	}

	public void upgrade() {
		if(level+1 <= maxLevel)
			level++;
		
		damage = damageLevels[level];
		attackRange = rangeLevels[level];
		attackRate = attackRateLevels[level];
		setAttackCooldown(attackRate);
	}

}
