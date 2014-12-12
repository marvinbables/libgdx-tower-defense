package gamedev.screen;

import gamedev.entity.Tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class TowerInformation {
	BitmapFont towerInfoFont, costFont;
	Sprite background, towerSprite, towerToPutSprite, upgradeBtn, sellBtn;
	String damage, cost, range, attackRate;
	Tower selectedDeployedTower;

	public TowerInformation() {
		damage = "";
		cost = "";
		range = "";
		attackRate = "";
		towerSprite = null;
		towerToPutSprite = null;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Minecraftia.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 12;
		parameter.flip = true;
		towerInfoFont = generator.generateFont(parameter); // font size 14 pixels
		parameter.size = 8;
		costFont = generator.generateFont(parameter);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		Texture tex = new Texture(Gdx.files.internal("assets/img/info_bg.png"));
		background = new Sprite(tex);
		background.setSize(250, 80);
		background.setPosition(300, 500);
		background.flip(false, true);
		
		Texture upgrade = new Texture(Gdx.files.internal("assets/img/upgrade_button.png"));
		upgradeBtn = new Sprite(upgrade);
		upgradeBtn.setPosition(300, 580);
		upgradeBtn.flip(false, true);
		
		Texture sell = new Texture(Gdx.files.internal("assets/img/sell_button.png"));
		sellBtn = new Sprite(sell);
		sellBtn.setPosition(425, 580);
		sellBtn.flip(false, true);
		
	}
	
	public void draw(SpriteBatch spriteBatch) {
		background.draw(spriteBatch);
		towerInfoFont.setColor(Color.WHITE);
		
		int x = 310, y = 510;
		towerInfoFont.draw(spriteBatch, "Cost: " + cost, x, y);
		towerInfoFont.draw(spriteBatch, "Damage: " + damage, x, y + 15);
		towerInfoFont.draw(spriteBatch, "Range: " + range, x, y + 30);
		towerInfoFont.draw(spriteBatch, "Speed: " + attackRate, x, y + 45);
		
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
		}
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(String attackRate) {
		this.attackRate = attackRate;
	}

	public Sprite getTowerSprite() {
		return towerSprite;
	}

	public void setTowerSprite(Sprite towerSprite) {
		this.towerSprite = towerSprite;
	}

	public Sprite getTowerToPutSprite() {
		return towerToPutSprite;
	}

	public void setTowerToPutSprite(Sprite towerToPutSprite) {
		this.towerToPutSprite = towerToPutSprite;
	}

	public Tower getSelectedDeployedTower() {
		return selectedDeployedTower;
	}

	public void setSelectedDeployedTower(Tower selectedDeployedTower) {
		this.selectedDeployedTower = selectedDeployedTower;
	}
	
}
