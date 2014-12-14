package gamedev.entity.tower;

import gamedev.entity.Tower;
import gamedev.td.GDSprite;

public class CurrencyTower extends Tower {
	
	private int maxLevel = 3;
	private static int damageLevels[] = {1};
	private static int rangeLevels[] = {0};
	private static float attackRateLevels[] = {2f};

	public CurrencyTower(GDSprite sprite, int level, int cost) {
		super(sprite, damageLevels[level], rangeLevels[level], attackRateLevels[level], cost, level, "Currency Tower");
		
	}

	public void upgrade() {
		if(level+1 <= maxLevel)
			level++;
		
		damage = damageLevels[level];
		attackRange = rangeLevels[level];
		attackRate = attackRateLevels[level];
	}
	

}
