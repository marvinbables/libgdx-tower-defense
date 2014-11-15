package gamedev.td;

import java.awt.Point;

import gamedev.entity.GameState;
import gamedev.screen.GameScreen;
import gamedev.screen.MainMenuScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(currentScreen.equals(gameScreen)) {
			Point point = getGridCoordinate(screenX, screenY);
			gameScreen.setHighlightCoord(point.x, point.y);
		}
		return false;
	}
	
	private Point getGridCoordinate(int screenX, int screenY) {
		Point p = new Point(-50, -50);
		
		for (int i = 0; i < gameScreen.getGameState().GRIDX; i++) {
			if(screenX <= i*gameScreen.getTileSize()) {
				p.x = (i-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		for (int j = 0; j < gameScreen.getGameState().GRIDY; j++) {
			if(screenY <= j*gameScreen.getTileSize()) {
				p.y = (j-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		return p;
	}
	
	

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
