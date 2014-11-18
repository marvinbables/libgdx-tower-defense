package gamedev.entity;

public class EnemyFactory {
	
	public Enemy makeEnemy(String enemyType){
		Enemy enemy = null;		
		enemyType = enemyType.toLowerCase();
		int health, moneyReward;
		float speed;
		
		if(enemyType.equals("spider")){
			health = 50;
			moneyReward = 10;
			speed = 1.5f;
			enemy = new Enemy(enemyType, health, moneyReward, speed);
		}
		else if (enemyType.equals("skeleton")){
			//TODO skeleton stats
			
		}
			
		
		return enemy;
		
	}

}
