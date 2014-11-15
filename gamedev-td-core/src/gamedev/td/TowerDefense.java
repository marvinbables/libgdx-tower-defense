package gamedev.td;

import gamedev.screen.GameScreen;
import gamedev.screen.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TowerDefense extends Game {

	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	
	Controller controller;
	
	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen();
		gameScreen = new GameScreen();
		
		setScreen(gameScreen);
		
		controller = new Controller(this.getScreen(), gameScreen, mainMenuScreen, gameScreen.getGameState());
		Gdx.input.setInputProcessor(controller);
		
	}

	@Override
	public void render () {
		super.render();
		
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
