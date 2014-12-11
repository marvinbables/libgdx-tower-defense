package gamedev.td;

import java.awt.Point;
import java.util.List;

import gamedev.entity.GameState;
import gamedev.entity.Tower;
import gamedev.screen.GameScreen;
import gamedev.screen.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Controller implements InputProcessor {
	
	// Dito lahat ng input
	
	// View
	private Screen currentScreen;
	private GameScreen gameScreen;
	private MainMenuScreen mainMenuScreen;
	
	// Model
	private GameState gameState;
	
	Tower towerToPut; // The tower to put when the player wants to deploy a tower

	public Controller(Screen currentScreen, GameScreen gameScreen,
			MainMenuScreen mainMenuScreen, GameState gameState) {
		this.currentScreen = currentScreen;
		this.gameScreen = gameScreen;
		this.mainMenuScreen = mainMenuScreen;
		this.gameState = gameState;
		towerToPut = null;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO: Get components of Screen
		// 		 For each component, check if mouseX mouseY is inside
		// 		 Call the appropriate action in GameState
		if(currentScreen.equals(gameScreen)) {
			List<GDSprite> availableTowers = gameScreen.getAvailableTowers();
			for (int i = 0; i < availableTowers.size(); i++) {
				Sprite sprite = availableTowers.get(i);
				if(Gdx.input.isButtonPressed(Buttons.LEFT))
					if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
							&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
						towerToPut = gameState.newTower(i);
						gameScreen.setTowerInfo(towerToPut);
						gameScreen.setTowerToPutSprite(i);
						gameScreen.setTowerInfoSprite(i);
						if(gameState.enoughMoney(towerToPut)){
							gameScreen.setDrawRadius(towerToPut.getAttackRange());
							gameScreen.cloneSprite(i);
						}
						else {
							gameScreen.setDrawRedHighlight(true);
							towerToPut = null;
							gameScreen.setTowerToPutSprite(-1);
							gameScreen.nullClonedTower();
						}
					}
			}
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
				Point point = getGridCoordinate(screenX, screenY);
				if(point.x != -50 && point.y != -50 && towerToPut != null && isPlaceable(point.x, point.y)) {
					gameState.getGrid()[point.x/40][point.y/40] = -1;
					towerToPut.setX(point.x);
					towerToPut.setY(point.y);
					if(gameState.enoughMoney(towerToPut)){
						towerToPut.setCenter((float) point.x + gameScreen.getTileSize() / 2, (float) point.y + gameScreen.getTileSize() / 2);
						gameState.deployTower(towerToPut);
						gameScreen.addDeployedTowerSprite();
						towerToPut = null;
						gameScreen.setTowerInfoSprite(-1);
						gameScreen.setTowerToPutSprite(-1);
						gameScreen.setTowerInfo(null);
					}
					else {
						towerToPut = null;
						gameScreen.setTowerInfoSprite(-1);
						gameScreen.setTowerToPutSprite(-1);
					}
					
				}
			}
			if (Gdx.input.isButtonPressed(Buttons.RIGHT)){
			    gameScreen.nullClonedTower();
			    gameScreen.setTowerInfo(null);
			    towerToPut = null;
			    gameScreen.setTowerInfoSprite(-1);
			    gameScreen.setTowerToPutSprite(-1);
			}
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(currentScreen.equals(gameScreen)) {
			Point point = getGridCoordinate(screenX, screenY);
			gameScreen.setHighlightCoord(point.x, point.y);
			gameScreen.setClonedTowerSpriteLoc(point.x, point.y);
			
			gameScreen.setDrawRedHighlight(false);
			List<GDSprite> towerSprites = gameScreen.getAvailableTowers();
			for (int i = 0; i < towerSprites.size(); i++) {
				Sprite sprite = towerSprites.get(i);
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
					// assumes size of sprites of available towers is equal to the size of the available towers model
					
					boolean showTooltip = true;
					
					if(towerToPut == null)
						gameScreen.drawTowerInfo(showTooltip, (int)sprite.getX(), (int)sprite.getY(), gameState.getAvailableTowers().get(i));
					else 
						gameScreen.drawTowerInfo(showTooltip, (int)sprite.getX(), (int)sprite.getY(), towerToPut);
					gameScreen.setTowerInfoSprite(i);
					break;
				}
				else {
					gameScreen.drawTowerInfo(false, -50, -50, towerToPut);
//					if(towerToPut != null)
//						gameScreen.setTowerInfoSprite(i);
//					else
					if(towerToPut == null)
						gameScreen.setTowerInfoSprite(-1);
						
				}
			}
			
			if(!isPlaceable(point.x, point.y)){
				gameScreen.getTowerRangeRenderer().setColor(1,0,0,.5f);
			}
			else gameScreen.getTowerRangeRenderer().setColor(1,1,1,.5f);
			
		}
		return false;
	}
	
	private Point getGridCoordinate(int screenX, int screenY) {
		Point p = new Point(-50, -50);
		
		for (int i = 0; i < GameState.GRIDX+1; i++) {
			if(screenX <= i*gameScreen.getTileSize()) {
				p.x = (i-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		for (int j = 0; j < GameState.GRIDY+1; j++) {
			if(screenY <= j*gameScreen.getTileSize()) {
				p.y = (j-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		return p;
	}
	
	

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

	public boolean isPlaceable(int x, int y){
		if(x < 0 || y < 0 || gameState.getGrid()[x/40][y/40] != 0)
			return false;
		else return true;
	}

}
