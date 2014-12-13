package gamedev.entity;

import gamedev.entity.Enemy.EnemyType;
import gamedev.entity.Tile.TileType;
import gamedev.entity.TowerFactory.TowerType;
import gamedev.level.Level;
import gamedev.td.Config;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameState {
	private static GameState instance;
	
	public static final int GRIDX = 17, GRIDY = 12;

	/**
	 * One full round lasts thirty (30) seconds.
	 */
	private static final float ROUND_DURATION = 30;
	
	private Level currentLevel;
	
	private int score = 0;
	private int money = 0;
	private int playerLife = 10;
	private int spawnedEnemies;
	
	private TileType grid[][];
	private float waveSpawnTime, beginRoundWaitTime = 5, spawnDelay;


	private boolean hasSpawned;
	
	private List<Enemy> enemies;
	private List<Integer> enemiesToBeSpawned;
	private List<Tower> deployedTowers, availableTowers;
	private List<Point> currentWaypoints;

	
	
	
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
		createMap();
	}
	
	private void createMap(){
		grid = new TileType[GRIDX][GRIDY];
		for (int i = 0; i < GRIDX; i++) {
			for (int j = 0; j < GRIDY; j++) {
				grid[i][j] = TileType.Null;
			}
		}
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
		playerLife = 10;
		spawnDelay = 0;
		spawnedEnemies = 0;
		waveSpawnTime = beginRoundWaitTime;
	}
	
	public void update(float delta) {
		if (hasSpawned == false)
			spawnEnemiesUpdate(delta);
		
		for(Enemy enemy : enemies)
			enemy.update(delta);
		
		
		for(Tower tower : deployedTowers)
			tower.update(delta);
	}
	
	private void spawnEnemiesUpdate(float delta) {
		if (waveSpawnTime > 0)
			waveSpawnTime -= delta;
		else
			waveSpawnTime = ROUND_DURATION;
	}

	public void render(SpriteBatch spriteBatch){
		displayMap(spriteBatch);
	}

	private void displayMap(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				TileType type = grid[i][j];
				
				Tile newTile = Tile.create(type);
				Vector2 position = new Vector2(i * Config.tileSize, j * Config.tileSize);
				newTile.setPosition(position);
				newTile.draw(spriteBatch);
			}
		}
		spriteBatch.end();
	}
	
	public boolean checkProjectileCollision() {
	
		return false;
	}
	
	public void prepareLevel(int lvl) {
		String level[] = Level.getInstance().getGrid();
		
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[y].length(); x++) {
				grid[x][y] = Tile.interpretType(Character.getNumericValue(level[y].charAt(x)));
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
			Enemy enemy = Enemy.createEnemy(EnemyType.Spider);
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
		return money >= tower.getCost();
	}
	

	public void setWaveSpawnTime(float waveSpawnTime) {
		if(waveSpawnTime < 0)
			this.waveSpawnTime = 30;
		else
			this.waveSpawnTime = waveSpawnTime;
	}

	public void getDamaged() {
		playerLife--;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public int getPlayerLife() {
		return playerLife;
	}
	
}
