package gamedev.screen;

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
import gamedev.td.helper.FontHelper;
import gamedev.td.helper.TimeHelper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameUserInterface {
	BitmapFont towerInfoFont, costFont;
	GDSprite uiBackground, infoBackground, towerToPutSprite, upgradeBtn, sellBtn,
			upgradeToCorruptedEgg, upgradeToSlime, upgradeToWood,
			upgradeToSand, upgradeToFireArrow, upgradeToIceArrow,
				tileHighlight, towerBtnHighlight, ghostTower, 
				waveLabel, towerLabel, moneyLabel;

	Tower selectedDeployedTower;

	TowerRangeRenderer towerRangeRenderer;
	
	private int userInterfaceY = 480;

	private ArrayList<GDSprite> btnsBuildTower;
	
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

		GDSprite dirtTower = spriteManager.getTower(TowerType.Dirt_Tower);
		GDSprite arrowTower = spriteManager.getTower(TowerType.Arrow_Tower);
		GDSprite eggTower = spriteManager.getTower(TowerType.Egg_Tower);
		GDSprite potionTower = spriteManager.getTower(TowerType.Potion_Tower);
		GDSprite currencyTower = spriteManager.getTower(TowerType.Currency_Tower);

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
		towerToPutSprite = null;
		ghostTower = null;
		FontHelper.initialize();
		towerInfoFont = FontHelper.minecraftia14px;
		costFont = FontHelper.minecraftia8px;
		
		SpriteManager spriteManager = SpriteManager.getInstance();
		
		towerLabel = spriteManager.getSprite("tower_label");
		towerLabel.setPosition(0, 13 * Config.tileSize);
		
		moneyLabel = spriteManager.getSprite("emerald");
		moneyLabel.setPosition(0, 14 * Config.tileSize);
		
		waveLabel = spriteManager.getSprite("wave");
		waveLabel.setPosition(5, 15 * Config.tileSize);

		uiBackground = spriteManager.getSprite("ui");
		uiBackground.setPosition(0, userInterfaceY);
		
		infoBackground = spriteManager.getSprite("info_bg");
		infoBackground.setPosition(300, userInterfaceY + 10);

		upgradeBtn = spriteManager.getSprite("upgrade_button");
		upgradeBtn.setPosition(300, userInterfaceY + 120);

		sellBtn = spriteManager.getSprite("sell_button");
		sellBtn.setPosition(425, userInterfaceY + 120);

		tileHighlight = spriteManager.getSprite("highlight");
		towerBtnHighlight = spriteManager.getSprite("tower_highlight");
		
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
			heartSprite[i].setPosition(i * 20 + i * 2 + 10, 12 * Config.tileSize + 10);
		}
	}

	public void render(SpriteBatch spriteBatch) {

	}

	public void update(float delta) {

	}

	private void initializeUpgradeButtons() {
		SpriteManager spriteManager = SpriteManager.getInstance();
		
		upgradeToWood = spriteManager.getSprite("upgrade_to_wood");
		upgradeToWood.setPosition(300, userInterfaceY + 120);

		upgradeToSand = spriteManager.getSprite("upgrade_to_sand");
		upgradeToSand.setPosition(425, userInterfaceY + 120);

		upgradeToFireArrow = spriteManager.getSprite("upgrade_to_fireArrow");
		upgradeToFireArrow.setPosition(300, userInterfaceY + 120);

		upgradeToIceArrow = spriteManager.getSprite("upgrade_to_iceArrow");
		upgradeToIceArrow.setPosition(425, userInterfaceY + 120);

		upgradeToSlime = spriteManager.getSprite("upgrade_to_slime");
		upgradeToSlime.setPosition(300, userInterfaceY + 120);

		upgradeToCorruptedEgg = spriteManager.getSprite("upgrade_to_cegg");
		upgradeToCorruptedEgg.setPosition(425, userInterfaceY + 120);
	}

	public void draw(SpriteBatch spriteBatch) {
		towerRangeRenderer.render(); // put towerRangeRenderer outside spriteBatch begin and end (issue in drawing)
		spriteBatch.begin();
		
		tileHighlight.draw(spriteBatch);
		
		uiBackground.draw(spriteBatch);
		infoBackground.draw(spriteBatch);
		
		towerLabel.draw(spriteBatch);
		moneyLabel.draw(spriteBatch);
		waveLabel.draw(spriteBatch);
		
		// Draw 'build tower' buttons
		for (GDSprite tower : btnsBuildTower)
			tower.draw(spriteBatch);

		if (towerToBuild != null){
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
		
		towerBtnHighlight.draw(spriteBatch);
		if(ghostTower != null) {
			ghostTower.draw(spriteBatch);
		}
		
		drawHealthBars(spriteBatch);
		towerInfoFont.draw(spriteBatch, GameState.getInstance().getMoney()+"", Config.tileSize + 5, 14 * Config.tileSize + 14);
		towerInfoFont.draw(spriteBatch, TimeHelper.formatSeconds(GameState.getInstance().getRoundTime()), Config.tileSize + 5, 15 * Config.tileSize + 12);
		
		spriteBatch.end();
		
		
	}

	private void drawTowerInfo(SpriteBatch spriteBatch) {
		towerInfoFont.setColor(Color.WHITE);

		// Descriptions
		int x = 310;
		if (towerToBuild != null) {
			towerInfoFont.draw(spriteBatch, towerName, x, userInterfaceY + 15);
			towerInfoFont.draw(spriteBatch, "Cost: " + cost, x, userInterfaceY + 32);
			towerInfoFont.draw(spriteBatch, "Damage: " + damage, x, userInterfaceY + 49);
			towerInfoFont.draw(spriteBatch, "Range: " + range, x, userInterfaceY + 66);
			towerInfoFont.draw(spriteBatch, "Speed: " + attackRate, x, userInterfaceY + 83);
		}
		
		// Image icon
//		if (towerSprite != null) {
//			towerSprite.setPosition(x + 150, userInterfaceY + 25);
//			towerSprite.draw(spriteBatch);
//		} 
		if (towerToPutSprite != null) {
			towerToPutSprite.setPosition(x + 150, userInterfaceY + 25);
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
		return btnsBuildTower;
	}

	public GDSprite getTileHighlight() {
		return tileHighlight;
	}

	public GDSprite getTowerBtnHighlight() {
		return towerBtnHighlight;
	}

	public void setTowerToBuild(Tower towerToBuild, TowerType towerType) {
		setTowerInfo(towerToBuild);
		
		SpriteManager spriteManager = SpriteManager.getInstance();
		towerToPutSprite = spriteManager.getTower(towerType);
		
		GameState state = GameState.getInstance();
		
		if (state.canBuyTower(towerToBuild)) {
			towerRangeRenderer.setTowerToBuild(towerToBuild);
		} else {
			towerToPutSprite = null;
			towerToBuild = null;
		}
	}

	public void setTowerToUpgrade(Tower tower) {
		
	}

	/**
	 * Reset; Deselect all towers to be built or upgraded.
	 */
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Change color of the highlighted cell
	 * @param point
	 */
	public void setHighlightedCell(Point point) {
		tileHighlight.setPosition(point.x, point.y);
		
	}

	/**
	 * Place a ghost tower on the highlighted cell
	 * Note that the attack range should be drawn as well
	 * @param point
	 */
	public void setGhostTowerLocation(Point point) {
		if(ghostTower != null) {
			ghostTower.setPosition(point.x, point.y);
		}
	}
	
	public void setGhostTower(TowerType towerType) {
		if(towerType != null)
			ghostTower = SpriteManager.getInstance().getTower(towerType);
		else ghostTower.setColor(Config.clearer);
	}
	
	public void setTowerRange(Tower towerToBuild) {
		if(towerToBuild != null)
			towerRangeRenderer.setAttackRange(towerToBuild.getAttackRange());
		else towerRangeRenderer.setAttackRange(0);
	}
}

	