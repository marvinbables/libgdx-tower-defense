package gamedev.entity;

import gamedev.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	public static final int GRIDX = 17, GRIDY = 12;
	
	private int currentLevel, score, grid[][], money;
	private float waveSpawnTime, SPAWN_TIME = 5;
	private List<Enemy> enemies;
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
		enemies = new ArrayList<Enemy>();
		towersDeployed = new ArrayList<Tower>();
		availableTowers = new ArrayList<Tower>();
		createTowers();
	}
	
	private void createTowers() {
		// Parameters: damage, attackRange, attackRate
		Tower dirtTower = new Tower(5, 50, 1);
		Tower arrowTower = new Tower(7, 50, 1);
		Tower eggTower = new Tower(7, 50, 1.3f);
		Tower potionTower = new Tower(5, 25, 0.9f); // ? 
		Tower currencyTower = new Tower(1, 20, 2f); // ?
		
		availableTowers.add(dirtTower);
		availableTowers.add(arrowTower);
		availableTowers.add(eggTower);
		availableTowers.add(potionTower);
		availableTowers.add(currencyTower);
	}
	
	public Tower newTower(int index) {
		Tower t = availableTowers.get(index);
		return new Tower(t.getDamage(), t.getAttackRange(), t.getAttackRate());
	}

	public void initGame() {
		currentLevel = 1;
		score = 0;
		money = 100;
		waveSpawnTime = SPAWN_TIME;
	}
	
	public void update(float delta) {
		
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
	
	public void prepareEnemies() {
		if(getWaveSpawnTime() == 0){
			if(getCurrentLevel() == 1){
				int instances = Level.level_1_enemies[0][0];
				for (int i = 0; i < instances; i++) {
					Enemy enemy = EnemyFactory.makeEnemy(Level.level_1_enemies[0][1], Level.level_1_waypoints);
					enemies.add(enemy);
				}
				
			}
			waveSpawnTime = SPAWN_TIME;
		}
	}
	
	
	
	
	public void deployTower(Tower tower) {
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
			this.waveSpawnTime = SPAWN_TIME;
		else
			this.waveSpawnTime = waveSpawnTime;
	}
	
}
