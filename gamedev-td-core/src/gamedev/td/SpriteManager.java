package gamedev.td;

import gamedev.entity.Enemy.EnemyType;
import gamedev.entity.GameState;
import gamedev.entity.TextureFactory;
import gamedev.entity.TowerFactory.TowerType;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SpriteManager {
	private static SpriteManager instance;
	
	private static HashMap<String, Texture> sprites;
	
	private static HashMap<String, Texture> tiles, towers, enemies;

	GDSprite highlightTile, uiSprite, towerLabel, heartSprite[], emeraldSprite,
			waveSprite, uiTowerHighlight, redHighlight, clonedTowerSprite,
			selectedSprite;

	public static SpriteManager getInstance() {
		if (instance == null)
			instance = new SpriteManager();
		return instance;
	}

	private SpriteManager() {
		sprites = new HashMap<String, Texture>();
		tiles = new HashMap<String, Texture>();
		towers = new HashMap<String, Texture>();
		initialize();
	}
	
	private void initialize(){
		
		initializeTiles();
		
		initializeTowers();
		
		initializeHighlightTile();
		
		initializeUserInterface();
		
	}


	private void initializeTowers() {
		GDSprite dirtTower = createSpriteTile("dirt_tower");
		GDSprite arrowTower = createSpriteTile("arrow_tower");
		GDSprite eggTower = createSpriteTile("egg_tower");
		GDSprite potionTower = createSpriteTile("potion_tower");
		GDSprite currencyTower = createSpriteTile("currency_tower");
		
		towers.put("dirt", dirtTower.getTexture());
		towers.put("arrow", arrowTower.getTexture());
		towers.put("egg", eggTower.getTexture());
		towers.put("potion", potionTower.getTexture());
		towers.put("currency", currencyTower.getTexture());
	}

	private void initializeUserInterface() {
		uiSprite = createSprite("ui");
		uiSprite.setPosition(0, GameState.GRIDY * Config.tileSize);

		for (int i = 0; i < heartSprite.length; i++) {
			heartSprite[i] = createSprite("heart");
			heartSprite[i].setPosition(i * 20 + i * 2 + 10, 12 * Config.tileSize + 10);
		}

		towerLabel = createSprite("tower_label");
		towerLabel.setPosition(0, 13 * Config.tileSize);

		emeraldSprite = createSprite("emerald");
		emeraldSprite.setPosition(0, 14 * Config.tileSize);

		waveSprite = createSprite("wave");
		waveSprite.setPosition(0, 15 * Config.tileSize);

		uiTowerHighlight = createSprite("tower_highlight");

		redHighlight = createSprite("red_highlight");
	}

	private void initializeHighlightTile() {
		Texture highlight = new Texture(Gdx.files.internal("assets/img/tile_highlight.png"));
		highlightTile = createTileFromTexture(highlight);
	}

	private GDSprite createTileFromTexture(Texture texture) {
		GDSprite sprite = new GDSprite(texture);
		sprite.setBounds(-50, -50, Config.tileSize, Config.tileSize);
		sprite.flip(false, true);
		return sprite;
	}

	private void initializeTiles() {
		GDSprite grass = createSpriteTile("grass");
		GDSprite dirt = createSpriteTile("dirt");
		GDSprite dark_dirt = createSpriteTile("dirt_dark");
		GDSprite steve = createSpriteTile("steve");
		
		tiles.put("grass", grass.getTexture());
		tiles.put("dirt", dirt.getTexture());
		tiles.put("dark_dirt", dark_dirt.getTexture());
		tiles.put("steve", steve.getTexture());
	}

	private static GDSprite createSpriteTile(String name){
		GDSprite sprite = createSprite(name);
		sprite.setBounds(-50, -50, Config.tileSize, Config.tileSize);
		return sprite;
	}
	
	/**
	 * Factory pattern to create sprites.
	 * @param name
	 * @return
	 */
	public static GDSprite createSprite(String name){
		GDSprite sprite = null;
		Texture texture = null;

		// If sprite is already registered, fetch the texture.
		if (sprites.containsKey(name))
			texture = sprites.get(name);
		
		// Create and register the texture
		else{
			texture = TextureFactory.createTexture(name);
			sprites.put(name, texture);
		}

		sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		
		sprite.setFlip(false, true);
		return sprite;
	}
	
	/**
	 * Private static method for when creating instances from the texture cache of towers, enemies, etc.
	 * @param texture
	 * @return
	 */
	private static GDSprite createSprite(Texture texture){
		GDSprite sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		
		sprite.setFlip(false, true);
		return sprite;
				
	}
	
	public GDSprite getTower(TowerType type) {
		Texture towerTexture = towers.get(type.toString());
		return createSprite(towerTexture);
	}
	
	public GDSprite getEnemy(EnemyType type) {
		Texture enemyTexture = enemies.get(type.toString());
		return createSprite(enemyTexture);
	}
}
