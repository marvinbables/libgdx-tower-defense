package gamedev.entity;

import gamedev.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GameState {
	public static final int GRIDX = 17, GRIDY = 12;
	
	private int currentLevel, score, grid[][], money;
	private float waveSpawnTime;
	private List<Enemy> enemies;
	private List<Tower> towersDeployed;
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
	}
	
	public void initGame() {
		currentLevel = 1;
		score = 0;
		money = 100;
		waveSpawnTime = 30;
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

	public void setTowersDeployed(List<Tower> towersDeployed) {
		this.towersDeployed = towersDeployed;
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
		if(this.waveSpawnTime - waveSpawnTime < 0)
			this.waveSpawnTime = 30;
		else
			this.waveSpawnTime = waveSpawnTime;
	}
	
}
