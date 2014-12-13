package gamedev.entity.tower;

import gamedev.entity.Tower;

public class EggTower extends Tower {

	private int maxLevel = 5;
	private static int damageLevels[] = {7};
	private static int rangeLevels[] = {100};
	private static float attackRateLevels[] = {1.3f};
	
	public EggTower(int level, int cost) {
		super(damageLevels[level], rangeLevels[level], attackRateLevels[level], cost, level, "Egg Tower");
	}

	public void upgrade() {
		if(level+1 <= maxLevel)
			level++;
		
		damage = damageLevels[level];
		attackRange = rangeLevels[level];
		attackRate = attackRateLevels[level];
	}

}
