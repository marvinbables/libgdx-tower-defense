package gamedev.entity;

import gamedev.entity.Enemy.EnemyType;
import gamedev.entity.TowerFactory.TowerType;
import gamedev.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	private static GameState instance;
	
	public static final int GRIDX = 17, GRIDY = 12;
	
	private Level currentLevel;
	private int score, grid[][], money, life = 10, spawnedEnemies;
	private float waveSpawnTime, SPAWN_TIME = 5, spawnDelay;
	private List<Enemy> enemies;
	private List<Integer> enemiesToBeSpawned;
	private List<Tower> deployedTowers, availableTowers;
	private List<Point> currentWaypoints;
	
	{
		grid = new int[GRIDX][GRIDY];
		for (int i = 0; i < GRIDX; i++) {
			for (int j = 0; j < GRIDY; j++) {
				grid[i][j] = -1;
			}
		}
	}
	
	public static GameState getInstance(){
		if (instance == null)
			instance = new GameState();
		return instance;
	}
	
	/**
	 * Game state cannot be instantiated outside of the class. To get a reference to this object, call the
	 * static method getInstance().
	 */
	private GameState() {
		instance = this;
		enemiesToBeSpawned = new ArrayList<Integer>();
		enemies = new ArrayList<Enemy>();
		deployedTowers = new ArrayList<Tower>();
		availableTowers = new ArrayList<Tower>();
		createTowers();
	}
	
	private void createTowers() {
		availableTowers.add( TowerFactory.createTower(TowerType.Dirt) );
		availableTowers.add( TowerFactory.createTower(TowerType.Arrow) );
		availableTowers.add( TowerFactory.createTower(TowerType.Egg) );
		availableTowers.add( TowerFactory.createTower(TowerType.Potion) );
		availableTowers.add( TowerFactory.createTower(TowerType.Currency) );
	}
	

	public void initialize() {
		currentLevel = Level.generateLevel(1);
		score = 0;
		money = 100;
		life = 10;
		spawnDelay = 0;
		spawnedEnemies = 0;
		waveSpawnTime = SPAWN_TIME;
	}
	
	public void update(float delta) {
		for(Enemy enemy : enemies)
			enemy.update(delta);
		
		
		for(Tower tower : deployedTowers)
			tower.update(delta);
	}
	
	public boolean checkProjectileCollision() {
	
		return false;
	}
	
	public void prepareLevel(int lvl) {
		String level[] = Level.getInstance().getGrid();
		
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
			Enemy enemy = Enemy.createEnemy(EnemyType.Spider, Level.level_1_waypoints);
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
		
		if(money >= tower.getCost()){
			money -= tower.getCost();
			deployedTowers.add(tower);
		}
		
	}
	
	public boolean enoughMoney(Tower tower){
		boolean enough = false;
		
		if(money >= tower.getCost()){
			enough = true;
		}
		
		return enough;
	}
	
	// Getters & Setters
	public Level getCurrentLevel() {
		return currentLevel;
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

	public List<Tower> getAvailableTowers() {
		return availableTowers;
	}

	public List<Tower> getDeployedTowers() {
		return deployedTowers;
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
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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

	public void getDamaged() {
		life--;
	}
	
}
