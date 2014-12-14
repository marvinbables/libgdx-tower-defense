package gamedev.entity.tower;

import gamedev.entity.Tower;
import gamedev.td.GDSprite;

public class WoodTower extends Tower {

	private int maxLevel = 5;
	private static int damageLevels[] = {20};
	private static int rangeLevels[] = {130};
	private static float attackRateLevels[] = {2.5f};
	
	public WoodTower(GDSprite sprite, int level, int cost) {
		super(sprite, damageLevels[level], rangeLevels[level], attackRateLevels[level], cost, level, "Wood Tower");
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
