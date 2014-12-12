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
				return new DirtTower(5, 80, 1, 20);
			case 1:
				return new ArrowTower(7, 80, 1, 30);
			case 2:
				return new EggTower(7, 100, 1.3f, 40);
			case 3:
				return new PotionTower(5, 100, 0.9f, 70);
			case 4:
				return new CurrencyTower(1, 0, 2f, 100);
		}
		
		return tower;
	}

}
