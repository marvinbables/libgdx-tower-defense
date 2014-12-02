package gamedev.entity;

import gamedev.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	public static final int GRIDX = 17, GRIDY = 12;
	
	private int currentLevel, score, grid[][], money, life, spawnedEnemies;
	private float waveSpawnTime, SPAWN_TIME = 1, spawnDelay;
	private List<Enemy> enemies;
	private List<Integer> enemiesToBeSpawned;
	private List<Tower> towersDeployed, availableTowers;
	private List<Point> currentWaypoints;
	
	{
		grid = new int[GRIDX][GRIDY];
		for (int i = 0; i < GRIDX; i++) {
			for (int j = 0; j < GRIDY; j++) {
				grid[i][j] = -1;
			}
		}
	}
	
	public GameState() {
		enemiesToBeSpawned = new ArrayList<Integer>();
		enemies = new ArrayList<Enemy>();
		towersDeployed = new ArrayList<Tower>();
		availableTowers = new ArrayList<Tower>();
		createTowers();
	}
	
	private void createTowers() {
		// Parameters: damage, attackRange, attackRate
		Tower dirtTower = new Tower(5, 50, 1, 20);
		Tower arrowTower = new Tower(7, 50, 1, 30);
		Tower eggTower = new Tower(7, 50, 1.3f, 40);
		Tower potionTower = new Tower(5, 25, 0.9f, 70); // ? 
		Tower currencyTower = new Tower(1, 20, 2f, 100); // ?
		
		availableTowers.add(dirtTower);
		availableTowers.add(arrowTower);
		availableTowers.add(eggTower);
		availableTowers.add(potionTower);
		availableTowers.add(currencyTower);
	}
	
	public Tower newTower(int index) {
		Tower t = availableTowers.get(index);
		return new Tower(t.getDamage(), t.getAttackRange(), t.getAttackRate(), t.getCost());
	}

	public void initGame() {
		currentLevel = 1;
		score = 0;
		money = 100;
		life = 20;
		spawnDelay = 0;
		spawnedEnemies = 0;
		waveSpawnTime = SPAWN_TIME;
	}
	
	public void update(float delta) {
		for (int i = enemies.size() - 1; i >= 0; i--){
			if(enemies.get(i).getWaypoints().size() == 0 && enemies.get(i).isActive()){
				enemies.get(i).setActive(false);
				life--;
			}
		}
		
		for(Enemy enemy : enemies){
			enemy.move();
		}
		
		
	}
	
	public boolean checkProjectileCollision() {
	
		return false;
	}
	
	public void prepareLevel(int lvl) {
		String level[] = Level.getLevel(lvl);
		
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[y].length(); x++) {
				grid[x][y] = Character.getNumericValue(level[y].charAt(x));
			}
		}
		
	}
	
	/*TODO
	 * prepare enemies gets the list of enemies, instances e.g. { {1,2} , {2,1} , {1,2} } 2 spiders, 1 skeleton, 2 spiders in order
	 */
	
	public boolean spawnEnemy(float delta){
		boolean spawned = false;
		int instances = enemiesToBeSpawned.size();
		spawnDelay += delta;
		if(spawnDelay >= .5 && spawnedEnemies < instances){
			Enemy enemy = EnemyFactory.makeEnemy(enemiesToBeSpawned.get(spawnedEnemies), Level.level_1_waypoints);
			enemies.add(enemy);
			spawnDelay = 0;
			++spawnedEnemies;
			spawned = true;
		}

		return spawned;

		
	}
	
	public void prepareEnemies() {
		if(getWaveSpawnTime() == 0){
			if(getCurrentLevel() == 1){
				for(int i = 0; i < Level.level_1_enemies.length; i++){
					for(int j = 0; j < Level.level_1_enemies[i][0]; j++)
						enemiesToBeSpawned.add(Level.level_1_enemies[i][1]);
				}
				
			}
			waveSpawnTime = 30;
		}
		


			
	}
	
	public void deployTower(Tower tower) {
		
		int temp = money -= tower.getCost();
		
		if(temp <= 0){
			money = 0;
		}
		else
		money = temp;
		
		System.out.println(money);
		towersDeployed.add(tower);
	}
	
	// Getters & Setters
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public List<Tower> getTowersDeployed() {
		return towersDeployed;
	}

	public List<Point> getCurrentWaypoints() {
		return currentWaypoints;
	}

	public void setCurrentWaypoints(List<Point> currentWaypoints) {
		this.currentWaypoints = currentWaypoints;
	}

	public int[][] getGrid() {
		return grid;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public float getWaveSpawnTime() {
		return waveSpawnTime;
	}

	public void setWaveSpawnTime(float waveSpawnTime) {
		if(waveSpawnTime < 0)
			this.waveSpawnTime = 30;
		else
			this.waveSpawnTime = waveSpawnTime;
	}
	
}
