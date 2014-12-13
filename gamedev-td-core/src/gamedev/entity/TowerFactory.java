package gamedev.entity;

import gamedev.entity.Tile.TileType;
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
		GDSprite towerSprite = handler.getTower(type);
		
		switch(type){
			case Dirt:
				// follow the format, for readability's sake
				int level = 0;
				int cost = 20;
				return new DirtTower(towerSprite, level, cost);
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
	public static TowerType interpretType(int val) {
		switch(val){
		case 0: return TowerType.Dirt;
		case 1: return TowerType.Arrow;
		case 2: return TowerType.Egg;
		case 3: return TowerType.Potion;
		case 4: return TowerType.Currency;
		}
		return TowerType.Dirt;
	}
}
