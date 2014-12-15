package gamedev.td;

import gamedev.entity.Enemy.EnemyType;
import gamedev.entity.GameState;
import gamedev.entity.Projectile.ProjectileType;
import gamedev.entity.TextureFactory;
import gamedev.entity.Tile.TileType;
import gamedev.entity.TowerFactory.TowerType;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SpriteManager {
	private static SpriteManager instance;
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
	 * Factory pattern to create sprites. Note that the name is turned into lower case.
	 * @param name
	 * @return
	 */
	private static GDSprite createSprite(String name){
		GDSprite sprite = null;
		name = name.toLowerCase();
		Texture texture = TextureFactory.createTexture(name);
		
		sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		
		sprite.setFlip(false, true);
		return sprite;
	}
	private GDSprite createSpriteProjectile(String name) {
		GDSprite sprite = null;
		name = name.toLowerCase();
		Texture texture = TextureFactory.createProjectile(name);
		
		sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		
		sprite.setFlip(false, true);
		return sprite;
	}
	
	public GDSprite getSprite(String name){
		return createSprite(name);
	}
	
	public GDSprite getTower(TowerType type) {
		return createSprite(type.toString());
	}
	
	public GDSprite getEnemy(EnemyType type) {
		return createSpriteTile(type.toString());
	}

	public GDSprite getTile(TileType type) {
		return createSpriteTile(type.toString());
	}
	
	public GDSprite getProjectile(ProjectileType type){
		return createSpriteProjectile(type.toString());
	}

	
}
