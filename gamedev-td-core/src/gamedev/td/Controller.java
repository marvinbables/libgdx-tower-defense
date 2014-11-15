package gamedev.td;

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
			
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
