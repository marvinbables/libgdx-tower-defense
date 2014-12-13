package gamedev.screen;

import java.util.ArrayList;

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
	Sprite background, towerSprite, towerToPutSprite, 
				upgradeBtn, sellBtn,
					upgradeToCorruptedEgg, upgradeToSlime, 
						upgradeToWood, upgradeToSand, 
							upgradeToFireArrow, upgradeToIceArrow;
	
	String towerName, damage, cost, range, attackRate;
	Tower selectedDeployedTower;
	
	TowerRangeRenderer towerRangeRenderer;
	
	private int y = 490;
	
	private ArrayList<GDSprite> btnsBuildTower;

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
		damage = "";
		cost = "";
		range = "";
		attackRate = "";
		towerName = "";
		towerSprite = null;
		towerToPutSprite = null;
		
		
		Texture tex = new Texture(Gdx.files.internal("assets/img/info_bg.png"));
		background = new Sprite(tex);
//		background.setSize(250, 80);
		background.setPosition(300, y);
		background.flip(false, true);
		
		Texture upgrade = new Texture(Gdx.files.internal("assets/img/upgrade_button.png"));
		upgradeBtn = new Sprite(upgrade);
		upgradeBtn.setPosition(300, y+100);
		upgradeBtn.flip(false, true);
		
		Texture sell = new Texture(Gdx.files.internal("assets/img/sell_button.png"));
		sellBtn = new Sprite(sell);
		sellBtn.setPosition(425, y+100);
		sellBtn.flip(false, true);

		towerRangeRenderer = new TowerRangeRenderer();
		
		initializeUpgradeButtons();
		initializeBuildTowerButtons();
	}
	
	public void render(SpriteBatch spriteBatch){
		
	}
	
	public void update(float delta){
		
	}
	
	private void initializeUpgradeButtons() {
		Texture cEgg = new Texture(Gdx.files.internal("assets/img/upgrade_to_cegg.png"));
		Texture slime = new Texture(Gdx.files.internal("assets/img/upgrade_to_slime.png"));
		Texture wood = new Texture(Gdx.files.internal("assets/img/upgrade_to_wood.png"));
		Texture sand = new Texture(Gdx.files.internal("assets/img/upgrade_to_sand.png"));
		Texture fireArrow = new Texture(Gdx.files.internal("assets/img/upgrade_to_fireArrow.png"));
		Texture iceArrow = new Texture(Gdx.files.internal("assets/img/upgrade_to_iceArrow.png"));
		
		upgradeToWood = new Sprite(wood);
		upgradeToWood.setPosition(300, y+120);
		upgradeToWood.flip(false, true);
		
		upgradeToSand = new Sprite(sand);
		upgradeToSand.setPosition(425, y+120);
		upgradeToSand.flip(false, true);
		
		upgradeToFireArrow = new Sprite(fireArrow);
		upgradeToFireArrow.setPosition(300, y+120);
		upgradeToFireArrow.flip(false, true);
		
		upgradeToIceArrow = new Sprite(iceArrow);
		upgradeToIceArrow.setPosition(425, y+120);
		upgradeToIceArrow.flip(false, true);
		
		upgradeToSlime = new Sprite(slime);
		upgradeToSlime.setPosition(300, y+120);
		upgradeToSlime.flip(false, true);
		
		upgradeToCorruptedEgg = new Sprite(cEgg);
		upgradeToCorruptedEgg.setPosition(425, y+120);
		upgradeToCorruptedEgg.flip(false, true);
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		
		background.draw(spriteBatch);
		
		// Draw build tower buttons
		for (GDSprite tower : btnsBuildTower)
			tower.draw(spriteBatch);
		

		towerInfoFont.setColor(Color.WHITE);
		
		int x = 310;
		towerInfoFont.draw(spriteBatch, towerName, x, y + 15);
		if(!cost.equals("")) {
			towerInfoFont.draw(spriteBatch, "Cost: " + cost, x, y + 30);
			towerInfoFont.draw(spriteBatch, "Damage: " + damage, x, y + 45);
			towerInfoFont.draw(spriteBatch, "Range: " + range, x, y + 60);
			towerInfoFont.draw(spriteBatch, "Speed: " + attackRate, x, y + 75);
		}
		
		if(towerSprite != null) {
			towerSprite.setPosition(x + 150, y + 10);
			towerSprite.draw(spriteBatch);
		}
		else if(towerToPutSprite != null) {
			towerToPutSprite.setPosition(x + 150, y + 10);
			towerToPutSprite.draw(spriteBatch);
		}
		
		if(selectedDeployedTower != null) {
			upgradeBtn.draw(spriteBatch);
			costFont.draw(spriteBatch, selectedDeployedTower.getUpgradeCost()+"", upgradeBtn.getX()+85, upgradeBtn.getY()+6);
			sellBtn.draw(spriteBatch);
			costFont.draw(spriteBatch, selectedDeployedTower.getSellCost()+"", sellBtn.getX()+75, sellBtn.getY()+6);
			
			if(selectedDeployedTower instanceof ArrowTower) {
				upgradeToFireArrow.draw(spriteBatch);
				upgradeToIceArrow.draw(spriteBatch);
			}
			else if(selectedDeployedTower instanceof DirtTower) {
				upgradeToWood.draw(spriteBatch);
				upgradeToSand.draw(spriteBatch);
			}
			else if(selectedDeployedTower instanceof EggTower) {
				upgradeToSlime.draw(spriteBatch);
				upgradeToCorruptedEgg.draw(spriteBatch);
			}
		}
		

		if (drawInfo) {
			uiTowerHighlight.draw(spriteBatch);
			// TODO: Draw tooltip showing information of the tower
		}

		for (int i = 0; i < gameState.getLife(); i++) {
			heartSprite[i].draw(spriteBatch);
		}

		if (clonedTowerSprite != null) {
			clonedTowerSprite.draw(spriteBatch);
		}

		userInterface.draw(spriteBatch);

		if (drawRedHighlight)
			redHighlight.draw(spriteBatch);

		
		towerRangeRenderer.render();
		spriteBatch.end();
	}
	

	public void drawTowerInfo(boolean b, int x, int y, Tower towerInfo) {
		uiTowerHighlight.setPosition(x, y);
		redHighlight.setPosition(x, y);
		drawInfo = b;
		setTowerInfo(towerInfo);
	}

	public void setTowerInfo(Tower towerInfo) {
		if (towerInfo != null) {
			userInterface.setDamage(towerInfo.getDamage() + "");
			userInterface.setCost(towerInfo.getCost() + "");
			userInterface.setRange(towerInfo.getAttackRange() + "");
			userInterface.setAttackRate(towerInfo.getAttackRate() + "");
			userInterface.setTowerName(towerInfo.getTowerName());
		} else {
			userInterface.setDamage("");
			userInterface.setCost("");
			userInterface.setRange("");
			userInterface.setAttackRate("");
			userInterface.setTowerName("");
		}
	}



	public void setTowerInfoSprite(int i) {
		userInterface.setTowerSprite(cloneSprite2(i));
	}
}
