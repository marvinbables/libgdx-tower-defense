package gamedev.entity.tower;

import gamedev.entity.Tower;
import gamedev.td.GDSprite;

public class SandTower extends Tower {

	private int maxLevel = 5;
	private static int damageLevels[] = {5};
	private static int rangeLevels[] = {90};
	private static float attackRateLevels[] = {5f};
	
	public SandTower(GDSprite sprite, int level, int cost) {
		super(sprite, damageLevels[level], rangeLevels[level], attackRateLevels[level], cost, level, "Sand Tower");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void upgrade() {
		if(level+1 <= maxLevel)
			level++;
		
		damage = damageLevels[level];
		attackRange = rangeLevels[level];
		attackRate = attackRateLevels[level];
	}


	

}
