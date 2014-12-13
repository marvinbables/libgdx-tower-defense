package gamedev.entity;

import gamedev.entity.tower.ArrowTower;
import gamedev.entity.tower.CurrencyTower;
import gamedev.entity.tower.DirtTower;
import gamedev.entity.tower.EggTower;
import gamedev.entity.tower.PotionTower;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;

public class TowerFactory {
	public enum TowerType {
		Dirt, Arrow, Egg, Potion, Currency
	}
	public static Tower createTower(TowerType type){
		Tower tower = null;
		
		SpriteManager handler = SpriteManager.getInstance();
		GDSprite towerSprite = handler.getTowerSprite(type);
		
		switch(type){
			case Dirt:
				return new DirtTower(towerSprite, 0, 20); // (level, cost)
			case Arrow:
				return new ArrowTower(towerSprite, 0, 30);
			case Egg:
				return new EggTower(towerSprite, 0, 40);
			case Potion:
				return new PotionTower(towerSprite, 0, 70);
			case Currency:
				return new CurrencyTower(towerSprite, 0, 100);
		}
		
		return tower;
	}

}
