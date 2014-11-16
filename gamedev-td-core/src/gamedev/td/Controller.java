package gamedev.td;

import java.awt.Point;
import java.util.List;

import gamedev.entity.GameState;
import gamedev.screen.GameScreen;
import gamedev.screen.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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

	public Controller(Screen currentScreen, GameScreen gameScreen,
			MainMenuScreen mainMenuScreen, GameState gameState) {
		this.currentScreen = currentScreen;
		this.gameScreen = gameScreen;
		this.mainMenuScreen = mainMenuScreen;
		this.gameState = gameState;
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
			List<Sprite> availableTowers = gameScreen.getAvailableTowers();
			for (int i = 0; i < availableTowers.size(); i++) {
				Sprite sprite = availableTowers.get(i);
				if(Gdx.input.isButtonPressed(Buttons.LEFT))
					if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
							&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
						gameScreen.cloneSprite(0);
					}
			}
			
			if (Gdx.input.isButtonPressed(Buttons.RIGHT)){
			    gameScreen.nullClonedTower();
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
			
			List<Sprite> towers = gameScreen.getAvailableTowers();
			for (Sprite sprite : towers) {
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
					gameScreen.drawToolTip(true, (int)sprite.getX(), (int)sprite.getY());
					break;
				}
				else
					gameScreen.drawToolTip(false, -50, -50);
			}
			
		}
		return false;
	}
	
	private Point getGridCoordinate(int screenX, int screenY) {
		Point p = new Point(-50, -50);
		
		for (int i = 0; i < gameScreen.getGameState().GRIDX+1; i++) {
			if(screenX <= i*gameScreen.getTileSize()) {
				p.x = (i-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		for (int j = 0; j < gameScreen.getGameState().GRIDY+1; j++) {
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

	

}
