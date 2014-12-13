package gamedev.entity;

import gamedev.entity.tower.ArrowTower;
import gamedev.entity.tower.CurrencyTower;
import gamedev.entity.tower.DirtTower;
import gamedev.entity.tower.EggTower;
import gamedev.entity.tower.PotionTower;

public class TowerFactory {
	
	public static Tower createTower(int type){
		Tower tower = null;
		switch(type){
			case 0:
				return new DirtTower(0, 20); // (level, cost)
			case 1:
				return new ArrowTower(0, 30);
			case 2:
				return new EggTower(0, 40);
			case 3:
				return new PotionTower(0, 70);
			case 4:
				return new CurrencyTower(0, 100);
		}
		
		return tower;
	}

}
