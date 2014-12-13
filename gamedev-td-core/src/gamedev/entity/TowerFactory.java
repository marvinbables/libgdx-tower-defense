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
		Dirt_Tower, Arrow_Tower, Egg_Tower, Potion_Tower, Currency_Tower
	}
	public static Tower createTower(TowerType type){
		Tower tower = null;
		
		SpriteManager handler = SpriteManager.getInstance();
		GDSprite towerSprite = handler.getTower(type);
		
		switch(type){
			case Dirt_Tower:
				// follow the format, for readability's sake
				int level = 0;
				int cost = 20;
				return new DirtTower(towerSprite, level, cost);
			case Arrow_Tower:
				return new ArrowTower(towerSprite, 0, 30);
			case Egg_Tower:
				return new EggTower(towerSprite, 0, 40);
			case Potion_Tower:
				return new PotionTower(towerSprite, 0, 70);
			case Currency_Tower:
				return new CurrencyTower(towerSprite, 0, 100);
		}
		
		return tower;
	}
	public static TowerType interpretType(int val) {
		switch(val){
		case 0: return TowerType.Dirt_Tower;
		case 1: return TowerType.Arrow_Tower;
		case 2: return TowerType.Egg_Tower;
		case 3: return TowerType.Potion_Tower;
		case 4: return TowerType.Currency_Tower;
		}
		return TowerType.Dirt_Tower;
	}
}
