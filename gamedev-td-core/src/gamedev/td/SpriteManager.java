package gamedev.td;

import gamedev.entity.Enemy.EnemyType;
import gamedev.entity.TowerFactory.TowerType;

public class SpriteManager {
	private static SpriteManager instance;
	
	public static SpriteManager getInstance() {
		if (instance == null)
			instance = new SpriteManager();
		return instance;
	}
	
	private SpriteManager(){
		
	}


	public GDSprite getTowerSprite(TowerType type) {
		// TODO Auto-generated method stub
		return null;
	}

	public GDSprite getEnemySprite(EnemyType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
