package gamedev.screen;

import java.util.ArrayList;
import java.util.List;

import gamedev.entity.GameState;
import gamedev.entity.Tower;
import gamedev.entity.TowerFactory.TowerType;
import gamedev.entity.tower.ArrowTower;
import gamedev.entity.tower.DirtTower;
import gamedev.entity.tower.EggTower;
import gamedev.screen.render.TowerRangeRenderer;
import gamedev.td.Config;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameUserInterface {
	BitmapFont towerInfoFont, costFont;
	Sprite background, towerSprite, towerToPutSprite, upgradeBtn, sellBtn,
			upgradeToCorruptedEgg, upgradeToSlime, upgradeToWood,
			upgradeToSand, upgradeToFireArrow, upgradeToIceArrow;

	Tower selectedDeployedTower;

	TowerRangeRenderer towerRangeRenderer;
	
	private int userInterfaceY = 490;

	private ArrayList<GDSprite> btnsBuildTower;
	private List<GDSprite> buildTowerButtons;
	
	// Tower information when building
	String towerName;
	private int damage;
	private int cost;
	private float range;
	private float attackRate;
	
	private Tower towerToBuild, towerToUpgrade;
	private GDSprite[] heartSprite;

	private void initializeBuildTowerButtons() {
		btnsBuildTower = new ArrayList<GDSprite>();
		SpriteManager spriteManager = SpriteManager.getInstance();

		GDSprite dirtTower = spriteManager.getTower(TowerType.Dirt);
		GDSprite arrowTower = spriteManager.getTower(TowerType.Arrow);
		GDSprite eggTower = spriteManager.getTower(TowerType.Egg);
		GDSprite potionTower = spriteManager.getTower(TowerType.Potion);
		GDSprite currencyTower = spriteManager.getTower(TowerType.Currency);

		int offset = 3, y = 13;
		dirtTower.setPosition(Config.tileSize, y * Config.tileSize);
		arrowTower.setPosition(Config.tileSize * 2 + offset, y * Config.tileSize);
		eggTower.setPosition(Config.tileSize * 3 + offset * 2, y * Config.tileSize);
		potionTower.setPosition(Config.tileSize * 4 + offset * 3, y * Config.tileSize);
		currencyTower.setPosition(Config.tileSize * 5 + offset * 4, y * Config.tileSize);

		btnsBuildTower.add(dirtTower);
		btnsBuildTower.add(arrowTower);
		btnsBuildTower.add(eggTower);
		btnsBuildTower.add(potionTower);
		btnsBuildTower.add(currencyTower);
	}

	public GameUserInterface() {
		setTowerInfo(null);
		towerSprite = null;
		towerToPutSprite = null;
		

		Texture tex = new Texture(Gdx.files.internal("assets/img/info_bg.png"));
		background = new Sprite(tex);
		// background.setSize(250, 80);
		background.setPosition(300, userInterfaceY);
		background.flip(false, true);

		Texture upgrade = new Texture(Gdx.files.internal("assets/img/upgrade_button.png"));
		upgradeBtn = new Sprite(upgrade);
		upgradeBtn.setPosition(300, userInterfaceY + 100);
		upgradeBtn.flip(false, true);

		Texture sell = new Texture(Gdx.files.internal("assets/img/sell_button.png"));
		sellBtn = new Sprite(sell);
		sellBtn.setPosition(425, userInterfaceY + 100);
		sellBtn.flip(false, true);

		towerRangeRenderer = new TowerRangeRenderer();
		
		initializeHeartSprites();
		initializeUpgradeButtons();
		initializeBuildTowerButtons();
	}

	private void initializeHeartSprites() {
		SpriteManager spriteManager = SpriteManager.getInstance();
		heartSprite = new GDSprite[10];
		for (int i = 0; i < 10; i ++){
			heartSprite[i] = spriteManager.getSprite("heart");
		}
	}

	public void render(SpriteBatch spriteBatch) {

	}

	public void update(float delta) {

	}

	private void initializeUpgradeButtons() {
		Texture cEgg = new Texture(Gdx.files.internal("assets/img/upgrade_to_cegg.png"));
		Texture slime = new Texture(Gdx.files.internal("assets/img/upgrade_to_slime.png"));
		Texture wood = new Texture(Gdx.files.internal("assets/img/upgrade_to_wood.png"));
		Texture sand = new Texture(Gdx.files.internal("assets/img/upgrade_to_sand.png"));
		Texture fireArrow = new Texture(Gdx.files.internal("assets/img/upgrade_to_fireArrow.png"));
		Texture iceArrow = new Texture(Gdx.files.internal("assets/img/upgrade_to_iceArrow.png"));

		upgradeToWood = new Sprite(wood);
		upgradeToWood.setPosition(300, userInterfaceY + 120);
		upgradeToWood.flip(false, true);

		upgradeToSand = new Sprite(sand);
		upgradeToSand.setPosition(425, userInterfaceY + 120);
		upgradeToSand.flip(false, true);

		upgradeToFireArrow = new Sprite(fireArrow);
		upgradeToFireArrow.setPosition(300, userInterfaceY + 120);
		upgradeToFireArrow.flip(false, true);

		upgradeToIceArrow = new Sprite(iceArrow);
		upgradeToIceArrow.setPosition(425, userInterfaceY + 120);
		upgradeToIceArrow.flip(false, true);

		upgradeToSlime = new Sprite(slime);
		upgradeToSlime.setPosition(300, userInterfaceY + 120);
		upgradeToSlime.flip(false, true);

		upgradeToCorruptedEgg = new Sprite(cEgg);
		upgradeToCorruptedEgg.setPosition(425, userInterfaceY + 120);
		upgradeToCorruptedEgg.flip(false, true);
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		background.draw(spriteBatch);

		if (towerToBuild != null){
			// Draw 'build tower' buttons
			for (GDSprite tower : btnsBuildTower)
				tower.draw(spriteBatch);
			
			// Draw tower descriptions
			drawTowerInfo(spriteBatch);
		}else if (towerToUpgrade != null){
			upgradeBtn.draw(spriteBatch);
			costFont.draw(spriteBatch, selectedDeployedTower.getUpgradeCost() + "", upgradeBtn.getX() + 85, upgradeBtn.getY() + 6);
			sellBtn.draw(spriteBatch);
			costFont.draw(spriteBatch, selectedDeployedTower.getSellCost() + "", sellBtn.getX() + 75, sellBtn.getY() + 6);

			if (selectedDeployedTower instanceof ArrowTower) {
				upgradeToFireArrow.draw(spriteBatch);
				upgradeToIceArrow.draw(spriteBatch);
			} else if (selectedDeployedTower instanceof DirtTower) {
				upgradeToWood.draw(spriteBatch);
				upgradeToSand.draw(spriteBatch);
			} else if (selectedDeployedTower instanceof EggTower) {
				upgradeToSlime.draw(spriteBatch);
				upgradeToCorruptedEgg.draw(spriteBatch);
			}
		}

		towerRangeRenderer.render();
		spriteBatch.end();
		
		drawHealthBars(spriteBatch);
	}

	private void drawTowerInfo(SpriteBatch spriteBatch) {
		towerInfoFont.setColor(Color.WHITE);

		// Descriptions
		int x = 310;
		if (towerToBuild != null) {
			towerInfoFont.draw(spriteBatch, towerName, x, userInterfaceY + 15);
			towerInfoFont.draw(spriteBatch, "Cost: " + cost, x, userInterfaceY + 30);
			towerInfoFont.draw(spriteBatch, "Damage: " + damage, x, userInterfaceY + 45);
			towerInfoFont.draw(spriteBatch, "Range: " + range, x, userInterfaceY + 60);
			towerInfoFont.draw(spriteBatch, "Speed: " + attackRate, x, userInterfaceY + 75);
		}
		
		// Image icon
		if (towerSprite != null) {
			towerSprite.setPosition(x + 150, userInterfaceY + 10);
			towerSprite.draw(spriteBatch);
		} else if (towerToPutSprite != null) {
			towerToPutSprite.setPosition(x + 150, userInterfaceY + 10);
			towerToPutSprite.draw(spriteBatch);
		}

	}

	private void drawHealthBars(SpriteBatch spriteBatch) {
		GameState gameState = GameState.getInstance();
		for (int i = 0; i < gameState.getPlayerLife(); i++) {
			heartSprite[i].draw(spriteBatch);
		}
	}

	public void setTowerInfo(Tower towerToBuild) {
		this.towerToBuild = towerToBuild;
		
		if (towerToBuild != null) {
			damage = towerToBuild.getDamage();
			cost = towerToBuild.getCost();
			range = towerToBuild.getAttackRange();
			attackRate = towerToBuild.getAttackRate();
			towerName = towerToBuild.getTowerName();
		} else {
			damage = 0;
			cost = 0;
			range = 0;
			attackRate = 0;
			towerName = "";
		}
	}

	public List<GDSprite> getBuildTowerButtons() {
		return buildTowerButtons;
	}

	public void setTowerToBuild(Tower towerToBuild, TowerType towerType) {
		setTowerInfo(towerToBuild);
		
		SpriteManager spriteManager = SpriteManager.getInstance();
		towerToPutSprite = spriteManager.getTower(towerType);
		
		GameState state = GameState.getInstance();
		
		if (state.enoughMoney(towerToBuild)) {
			towerRangeRenderer.setAttackRange(towerToBuild.getAttackRange());
		} else {
			towerToPutSprite = null;
		}
	}
}
