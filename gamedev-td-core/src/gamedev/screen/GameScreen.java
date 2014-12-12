package gamedev.screen;

import gamedev.entity.GameState;
import gamedev.entity.SpriteFactory;
import gamedev.entity.TextureFactory;
import gamedev.entity.Tower;
import gamedev.input.GameInputProcessor;
import gamedev.level.Level;
import gamedev.td.GDSprite;
import gamedev.td.TowerDefense;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
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

public class GameScreen extends GDScreen {

	/*
	 * Screen is view (MVC)
	 * 
	 * Display UI
	 * Display score, money, etc
	 * Display enemy textures
	 * Display tower textures
	 * Display map
	 */
	
	TowerDefense towerDefense;
	
	GameState gameState; // Model
	OrthographicCamera camera;
	
	SpriteBatch spriteBatch;
	GDSprite highlightTile, uiSprite, towerLabel, heartSprite[],
						emeraldSprite, waveSprite, uiTowerHighlight, redHighlight,
						clonedTowerSprite, selectedSprite; // clonedTowerSprite - the sprite that the mouse holds when he wants to deploy a tower
	List<GDSprite> tiles, availableTowers, deployedTowerSprites, enemySprites, spawnedEnemySprites;
	
	ShapeRenderer towerRangeRenderer;
	
	TowerInformation uiInformation;

	BitmapFont font;
	
	public static final int tileSize = 40;
	
	float sec = 0, 
			rangeRadius = 0; // Used in drawing the tower range

	boolean drawInfo = false, drawRedHighlight = false;
	private boolean spawn;
	
	public GameScreen(TowerDefense towerDefense) {
		this.towerDefense = towerDefense;
		towerRangeRenderer = new ShapeRenderer();
		towerRangeRenderer.setColor(1, 1, 1, .5f);
		gameState = new GameState();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		
		uiInformation = new TowerInformation();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		initializeFont();
		gameState.initGame();
		gameState.prepareLevel(1);
		initializeSprites();
		this.inputProcessor = new GameInputProcessor(this, towerDefense);
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
		selectedSprite = null;
		clonedTowerSprite = null;
		deployedTowerSprites = new ArrayList<GDSprite>();
		tiles = new ArrayList<GDSprite>();
		heartSprite = new GDSprite[10];
		

		
		GDSprite grassTile = SpriteFactory.createSpriteTile("grass");
		GDSprite dirtTile = SpriteFactory.createSpriteTile("dirt");
		GDSprite dirtDarkTile = SpriteFactory.createSpriteTile("dirt_dark");
		GDSprite steveTile = SpriteFactory.createSpriteTile("steve");
		
		tiles.add(grassTile);
		tiles.add(dirtTile);
		tiles.add(dirtDarkTile);
		tiles.add(steveTile);
		
		Texture highlight = new Texture(Gdx.files.internal("assets/img/tile_highlight.png"));
		highlightTile = createTile(highlight);
		
		uiSprite = SpriteFactory.createSprite("ui");
		uiSprite.setPosition(0, GameState.GRIDY*tileSize);
		
		for (int i = 0; i < heartSprite.length; i++) {
			heartSprite[i] = SpriteFactory.createSprite("heart");;
			heartSprite[i].setPosition(i*20 + i*2 + 10, 12*tileSize + 10);
		}
		
		towerLabel = SpriteFactory.createSprite("tower_label");
		towerLabel.setPosition(0, 13*tileSize);
		
		emeraldSprite = SpriteFactory.createSprite("emerald");
		emeraldSprite.setPosition(0, 14*tileSize);
		
		waveSprite = SpriteFactory.createSprite("wave");
		waveSprite.setPosition(0, 15*tileSize);
		
		uiTowerHighlight = SpriteFactory.createSprite("tower_highlight");
		
		redHighlight = SpriteFactory.createSprite("red_highlight");
		
		initializeTowerSprites();
		initializeEnemySprites();
	}

	private void initializeEnemySprites() {
		spawnedEnemySprites = new ArrayList<GDSprite>();
		enemySprites = new ArrayList<GDSprite>();
		
		 //TODO: change spider asset
		
		GDSprite spider = SpriteFactory.createSprite("spider");
		
		//This isn't used?
		enemySprites.add(spider);
		
		
	}

	private void initializeTowerSprites() {
		availableTowers = new ArrayList<GDSprite>();
		
		GDSprite dirtTower = SpriteFactory.createSpriteTile("dirt_tower");
		GDSprite arrowTower = SpriteFactory.createSpriteTile("arrow_tower");
		GDSprite eggTower = SpriteFactory.createSpriteTile("egg_tower");
		GDSprite potionTower = SpriteFactory.createSpriteTile("potion_tower");
		GDSprite currencyTower = SpriteFactory.createSpriteTile("currency_tower");
		
		
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
		
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		
		towerRangeRenderer.begin(ShapeType.Filled);
		if(selectedSprite != null) {
			towerRangeRenderer.circle(selectedSprite.getX() + tileSize / 2, convertYforShapeRenderer(selectedSprite.getY() + tileSize * 3 / 2) , rangeRadius);
		}
		else if(clonedTowerSprite != null) {
			if(clonedTowerSprite.getX() != -50 && clonedTowerSprite.getY() != -50){
				towerRangeRenderer.circle(clonedTowerSprite.getX() + tileSize / 2, convertYforShapeRenderer(clonedTowerSprite.getY() + tileSize * 3 / 2) , rangeRadius);
			}
		}
		
		towerRangeRenderer.end();
			//System.out.println(clonedTowerSprite.getX() + " " + clonedTowerSprite.getY());

		spriteBatch.begin();
			if(clonedTowerSprite == null)
				highlightTile.draw(spriteBatch);
			
			uiSprite.draw(spriteBatch);
			
			for (GDSprite tower : availableTowers) {
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
			
			
			for (GDSprite sprite : deployedTowerSprites) {
				sprite.draw(spriteBatch);
			}
			
			if(drawInfo) {
				uiTowerHighlight.draw(spriteBatch);
				// TODO: Draw tooltip showing information of the tower
			}
			
			for (int i = 0; i < gameState.getLife(); i++) {
				heartSprite[i].draw(spriteBatch);
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
			
			uiInformation.draw(spriteBatch);
			
			if(drawRedHighlight)
				redHighlight.draw(spriteBatch);
			
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
	
	private GDSprite newEnemySprite(int enemyType) {
		String path = "";
		
		switch(enemyType) {
			case 1: path = "assets/img/spider.png";
			default: path = "assets/img/spider.png";
		}
		
		Texture texture = new Texture(Gdx.files.internal(path));
		GDSprite sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		return sprite;
	}
	
	// Factory
	private GDSprite createTile(Texture texture) {
		GDSprite sprite = new GDSprite(texture);
		// tile size 40x40
		sprite.setBounds(-50, -50, tileSize, tileSize);
		sprite.flip(false, true);
		return sprite;
	}
	
	private GDSprite getSprite(int correspondingNumber) {
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
	
	public List<GDSprite> getDeployedTowerSprites() {
		return deployedTowerSprites;
	}

	public List<GDSprite> getAvailableTowers() {
		return availableTowers;
	}
	
	public TowerInformation getUiInformation() {
		return uiInformation;
	}

	public void drawTowerInfo(boolean b, int x, int y, Tower towerInfo) {
		uiTowerHighlight.setPosition(x, y);
		redHighlight.setPosition(x, y);
		drawInfo = b;
		setTowerInfo(towerInfo);
	}
	
	public void setTowerInfo(Tower towerInfo) {
		if(towerInfo != null) {
			uiInformation.setDamage(towerInfo.getDamage()+"");
			uiInformation.setCost(towerInfo.getCost()+"");
			uiInformation.setRange(towerInfo.getAttackRange()+"");
			uiInformation.setAttackRate(towerInfo.getAttackRate()+"");
		}
		else {
			uiInformation.setDamage("");
			uiInformation.setCost("");
			uiInformation.setRange("");
			uiInformation.setAttackRate("");
		}
	}
	
	public void setTowerInfoSprite(int i) {
		uiInformation.setTowerSprite(cloneSprite2(i));
	}
	
	public GDSprite cloneSprite(GDSprite sprite) {
		GDSprite newSprite = new GDSprite(sprite.getTexture());
		newSprite.setPosition(sprite.getX(), sprite.getY());
		newSprite.setFlip(false, true);
		return newSprite;
	}
	
	public void setTowerToPutSprite(int i) {
		uiInformation.setTowerToPutSprite(cloneSprite2(i));
	}
	
	private GDSprite cloneSprite2(int index) {
		if(index < 0)
			return null;
		return createTile(availableTowers.get(index).getTexture());
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
		rangeRadius = attackRange;
	}

	public ShapeRenderer getTowerRangeRenderer() {
		return towerRangeRenderer;
	}

	public void setDrawRedHighlight(boolean drawRedHighlight) {
		this.drawRedHighlight = drawRedHighlight;
	}
	
	public void setSelectedSprite(GDSprite sprite) {
		selectedSprite = sprite;
	}

	public void setSelectedTower(Tower selectedTower) {
		uiInformation.setSelectedDeployedTower(selectedTower);
	}

	
}
