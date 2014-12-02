package gamedev.screen;

import gamedev.entity.Enemy;
import gamedev.entity.GameState;
import gamedev.level.Level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {

	/*
	 * Screen is view (MVC)
	 * 
	 * Display UI
	 * Display score, money, etc
	 * Display enemy textures
	 * Display tower textures
	 * Display map
	 */
	
	GameState gameState; // Model
	OrthographicCamera camera;
	
	SpriteBatch spriteBatch;
	Sprite highlightTile, uiSprite, towerLabel, 
						emeraldSprite, waveSprite, uiTowerHighlight, 
						clonedTowerSprite; // clonedTowerSprite - the sprite that the mouse holds when he wants to deploy a tower
	List<Sprite> tiles, availableTowers, deployedTowerSprites, enemySprites, spawnedEnemySprites;
	
	ShapeRenderer towerRangeRenderer;

	BitmapFont font;
	
	public static final int tileSize = 40;
	
	float sec = 0, 
			rangeRadius = 0; // Used in drawing the tower range

	boolean drawToolTip = false;
	private boolean spawn;
	
	public GameScreen() {
		towerRangeRenderer = new ShapeRenderer();
		gameState = new GameState();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		initializeSprites();
		initializeFont();
		gameState.initGame();
		gameState.prepareLevel(1);
	}
	
	private void initializeFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Minecraftia.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.flip = true;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	private void initializeSprites() {
		clonedTowerSprite = null;
		deployedTowerSprites = new ArrayList<Sprite>();
		tiles = new ArrayList<Sprite>();
		
		Texture grass = new Texture(Gdx.files.internal("assets/img/grass.png"));
		Texture dirt = new Texture(Gdx.files.internal("assets/img/dirt_light.png"));
		Texture dirtDark = new Texture(Gdx.files.internal("assets/img/dirt_dark.png"));
		Texture steve = new Texture(Gdx.files.internal("assets/img/steve.png"));
		
		Sprite grassTile = createTile(grass);
		Sprite dirtTile = createTile(dirt);
		Sprite dirtDarkTile = createTile(dirtDark);
		Sprite steveTile = createTile(steve);
		
		tiles.add(grassTile);
		tiles.add(dirtTile);
		tiles.add(dirtDarkTile);
		tiles.add(steveTile);
		
		Texture highlight = new Texture(Gdx.files.internal("assets/img/tile_highlight.png"));
		highlightTile = createTile(highlight);
		
		Texture ui = new Texture(Gdx.files.internal("assets/img/ui.png"));
		uiSprite = new Sprite(ui);
		uiSprite.flip(false, true);
		uiSprite.setPosition(0, GameState.GRIDY*tileSize);
		
		Texture tower = new Texture(Gdx.files.internal("assets/img/tower_label.png"));
		towerLabel = createTile(tower);
		towerLabel.setPosition(0, 13*tileSize);
		
		Texture emeraldTex = new Texture(Gdx.files.internal("assets/img/emerald.png"));
		emeraldSprite = createTile(emeraldTex);
		emeraldSprite.setPosition(0, 14*tileSize);
		
		Texture waveTex = new Texture(Gdx.files.internal("assets/img/wave.png"));
		waveSprite = createTile(waveTex);
		waveSprite.setPosition(0, 15*tileSize);
		
		Texture uiTowerTex = new Texture(Gdx.files.internal("assets/img/ui_tower_highlight.png"));
		uiTowerHighlight = createTile(uiTowerTex);
		
		initializeTowerSprites();
		initializeEnemySprites();
	}

	private void initializeEnemySprites() {
		spawnedEnemySprites = new ArrayList<Sprite>();
		enemySprites = new ArrayList<Sprite>();
		
		Texture spiderTexture = new Texture(Gdx.files.internal("assets/img/spiderTemp.png")); //TODO: change spider asset
		
		Sprite spider = createTile(spiderTexture);
		
		enemySprites.add(spider);
		
		
	}

	private void initializeTowerSprites() {
		availableTowers = new ArrayList<Sprite>();
		
		Texture dirt = new Texture(Gdx.files.internal("assets/img/dirt_tower.png"));
		Texture arrow = new Texture(Gdx.files.internal("assets/img/arrow_tower.png"));
		Texture egg = new Texture(Gdx.files.internal("assets/img/egg_tower.png"));
		Texture potion = new Texture(Gdx.files.internal("assets/img/potion_tower.png"));
		Texture currency = new Texture(Gdx.files.internal("assets/img/currency_tower.png"));
		
		Sprite dirtTower = createTile(dirt);
		Sprite arrowTower = createTile(arrow);
		Sprite eggTower = createTile(egg);
		Sprite potionTower = createTile(potion);
		Sprite currencyTower = createTile(currency);
		
		
		int offset = 3, y = 13;
		dirtTower.setPosition(tileSize, y*tileSize);
		arrowTower.setPosition(tileSize*2 + offset, y*tileSize);
		eggTower.setPosition(tileSize*3 + offset*2, y*tileSize);
		potionTower.setPosition(tileSize*4 + offset*3, y*tileSize);
		currencyTower.setPosition(tileSize*5 + offset*4, y*tileSize);
		
		availableTowers.add(dirtTower);
		availableTowers.add(arrowTower);
		availableTowers.add(eggTower);
		availableTowers.add(potionTower);
		availableTowers.add(currencyTower);
	}
	
	public void cloneSprite(int index) {
		clonedTowerSprite = createTile(availableTowers.get(index).getTexture());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		update(delta);
		
		

		spriteBatch.begin();
			int grid[][] = gameState.getGrid();
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					getSprite(grid[i][j]).setPosition(i*tileSize, j*tileSize);
					getSprite(grid[i][j]).draw(spriteBatch);
				}
			}
		spriteBatch.end();
		
		if(clonedTowerSprite != null) {
			Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
			
			towerRangeRenderer.begin(ShapeType.Filled);
				towerRangeRenderer.setColor(1, 1, 1, .5f);
			if(clonedTowerSprite.getX() != -50 && clonedTowerSprite.getY() != -50){
				towerRangeRenderer.circle(clonedTowerSprite.getX() + tileSize / 2, convertYforShapeRenderer(clonedTowerSprite.getY() + tileSize * 3 / 2) , rangeRadius);
			}
			towerRangeRenderer.end();
			//System.out.println(clonedTowerSprite.getX() + " " + clonedTowerSprite.getY());
		}

		spriteBatch.begin();
		
			highlightTile.draw(spriteBatch);
			uiSprite.draw(spriteBatch);
			
			for (Sprite tower : availableTowers) {
				tower.draw(spriteBatch);
			}
			
			towerLabel.draw(spriteBatch);
			emeraldSprite.draw(spriteBatch);
			
			// View accesses the model (GameState). Tinamad na sa MVC
			font.setColor(new Color(65/255f, 243/255f, 132/255f, 1));
			font.draw(spriteBatch, ""+gameState.getMoney(), emeraldSprite.getX() + tileSize+3, emeraldSprite.getY() + 15);
			font.setColor(Color.BLACK);
			font.draw(spriteBatch, convertSecToMinSec(gameState.getWaveSpawnTime()), waveSprite.getX() + tileSize+3, waveSprite.getY() + 15);
			
			waveSprite.draw(spriteBatch);
			
			
			for (Sprite sprite : deployedTowerSprites) {
				sprite.draw(spriteBatch);
			}
			
			if(drawToolTip) {
				uiTowerHighlight.draw(spriteBatch);
				// TODO: Draw tooltip showing information of the tower
			}
			
			if(clonedTowerSprite != null) {
				clonedTowerSprite.draw(spriteBatch);
			}
			
			// Move enemy sprite
			for (int i = 0; i < spawnedEnemySprites.size(); i++) {
				if(gameState.getEnemies().get(i).isActive()){
					spawnedEnemySprites.get(i).setX(gameState.getEnemies().get(i).getX());
					spawnedEnemySprites.get(i).setY(gameState.getEnemies().get(i).getY());
					spawnedEnemySprites.get(i).setRotation(gameState.getEnemies().get(i).getAngle());
					spawnedEnemySprites.get(i).draw(spriteBatch);
				}
			}
			
		spriteBatch.end();
		
		
			
	}
	
	private void update(float delta) {
		
		sec += delta;
		if(sec >= 1) {
			gameState.setWaveSpawnTime(gameState.getWaveSpawnTime()-1);
			sec -= 1;
		}
		
		if(gameState.getWaveSpawnTime() == 0){
			spawn = true;
			gameState.prepareEnemies();
		}
		if(spawn){
			if(gameState.spawnEnemy(delta))
				spawnedEnemySprites.add(newEnemySprite(Level.level_1_enemies[0][1]));
		}
		
		gameState.update(delta);
		
	}

	private float convertYforShapeRenderer(float y) {
		return Gdx.graphics.getWidth() - y;
	
	}
	
	private String convertSecToMinSec(float timeInSec) {
		String time = "";
		float minute = timeInSec/60;
		float sec = timeInSec % 60;
		if(minute < 10)
			time += "0" + (int)minute;
		else
			time += (int)minute;
		
		time += ":";
		
		if(sec < 10)
			time += "0" + (int)sec;
		else
			time += (int)sec;
		
		return time;
	}
	
	public void setHighlightCoord(int x, int y) {
		highlightTile.setPosition(x, y);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
	private Sprite newEnemySprite(int enemyType) {
		String path = "";
		
		switch(enemyType) {
			case 1: path = "assets/img/spider.png";
			default: path = "assets/img/spider.png";
		}
		
		Texture texture = new Texture(Gdx.files.internal(path));
		Sprite sprite = new Sprite(texture);
		sprite.setPosition(-50, -50);
		return sprite;
	}
	
	// Factory
	private Sprite createTile(Texture texture) {
		Sprite sprite = new Sprite(texture);
		// tile size 40x40
		sprite.setBounds(-50, -50, tileSize, tileSize);
		sprite.flip(false, true);
		return sprite;
	}
	
	private Sprite getSprite(int correspondingNumber) {
		if(correspondingNumber >= tiles.size())
			return tiles.get(0);
		
		switch(correspondingNumber) {
			case 0: return tiles.get(0);
			case 1: return tiles.get(1);
			case 2: return tiles.get(2);
			case 3: return tiles.get(3);
			default: return tiles.get(0);
		}
	}

	public int getTileSize() {
		return tileSize;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public List<Sprite> getAvailableTowers() {
		return availableTowers;
	}

	public void drawToolTip(boolean b, int x, int y) {
		uiTowerHighlight.setPosition(x, y);
		drawToolTip = b;
	}

	public void setClonedTowerSpriteLoc(int x, int y) {
		if(clonedTowerSprite != null)
			clonedTowerSprite.setPosition(x, y);
	}
	
	public void nullClonedTower() {
		clonedTowerSprite = null;
	}
	
	public void addDeployedTowerSprite() {
		if(clonedTowerSprite != null) {
			deployedTowerSprites.add(clonedTowerSprite);
			clonedTowerSprite = null;
		}
	}

	public void setDrawRadius(float attackRange) {
		rangeRadius = 2*attackRange;
	}
}
