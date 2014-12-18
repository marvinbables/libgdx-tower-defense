package gamedev.entity;

import gamedev.entity.tower.ArrowTower;
import gamedev.entity.tower.CorruptedEggTower;
import gamedev.entity.tower.CurrencyTower;
import gamedev.entity.tower.DirtTower;
import gamedev.entity.tower.EggTower;
import gamedev.entity.tower.FireArrowTower;
import gamedev.entity.tower.IceArrowTower;
import gamedev.entity.tower.PotionTower;
import gamedev.entity.tower.SandTower;
import gamedev.entity.tower.WoodTower;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;

public class TowerFactory {
	public enum TowerType {
		Dirt_Tower, Arrow_Tower, Egg_Tower, Potion_Tower, Currency_Tower, Corrupted_Egg_Tower,
		Ice_Arrow_Tower, Fire_Arrow_Tower, Sand_Tower, Wood_Tower
	}
	public static Tower createTower(TowerType type){
		Tower tower = null;
		
		SpriteManager handler = SpriteManager.getInstance();
		GDSprite towerSprite = handler.getTower(type);
		
		int level = 0, cost = -1;
		
		switch(type){
			case Dirt_Tower:
				// follow the format, for readability's sake
				cost = 20;
				return new DirtTower(towerSprite, level, cost);
			case Arrow_Tower:
				cost = 30;
				return new ArrowTower(towerSprite, level, cost);
			case Egg_Tower:
				cost = 40;
				return new EggTower(towerSprite, level, cost);
			case Potion_Tower:
				cost = 70;
				return new PotionTower(towerSprite, level, cost);
			case Currency_Tower:
				 cost = 100;
				return new CurrencyTower(towerSprite, level, cost);
			case Corrupted_Egg_Tower:
				cost = 150;
				return new CorruptedEggTower(towerSprite, level, cost);
			case Fire_Arrow_Tower:
				cost = 200;
				return new IceArrowTower(towerSprite, level, cost);
			case Ice_Arrow_Tower:
				cost = 250;
				return new FireArrowTower(towerSprite, level, cost);
			case Sand_Tower:
				cost = 120;
				return new SandTower(towerSprite, level, cost);
			case Wood_Tower:
				cost = 150;
				return new WoodTower(towerSprite, level, cost);
				
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
		case 5: return TowerType.Corrupted_Egg_Tower;
		case 6: return TowerType.Ice_Arrow_Tower;
		case 7: return TowerType.Fire_Arrow_Tower;
		}
		return TowerType.Dirt_Tower;
	}
	public static TowerType interpretType(String name) {
		if(name.equals("Dirt Tower"))
			return TowerType.Dirt_Tower;
		else if(name.equals("Arrow Tower"))
			return TowerType.Arrow_Tower;
		else if(name.equals("Egg Tower"))
			return TowerType.Egg_Tower;
		else if(name.equals("Potion Tower"))
			return TowerType.Potion_Tower;
		else if(name.equals("Currency Tower"))
			return TowerType.Currency_Tower;
		else if(name.equals("Corrupted Egg Tower"))
			return TowerType.Corrupted_Egg_Tower;
		else if(name.equals("Ice Arrow Tower"))
			return TowerType.Ice_Arrow_Tower;
		else if(name.equals("Fire Arrow Tower"))
			return TowerType.Fire_Arrow_Tower;
		return null; 
	}

	
}
