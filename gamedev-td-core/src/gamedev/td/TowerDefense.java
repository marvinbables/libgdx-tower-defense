package gamedev.td;

import gamedev.screen.GDScreen;
import gamedev.screen.GameScreen;
import gamedev.screen.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TowerDefense extends Game {

	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	
	Controller controller;
	
	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen();
		gameScreen = new GameScreen();
		
		setScreen(gameScreen);
		
		controller = new Controller(this.getScreen(), gameScreen, mainMenuScreen, gameScreen.getGameState());
		Gdx.input.setInputProcessor(controller);
		
	}
	
	public void switchScreen(GDScreen screen){
		setScreen(screen);
		Gdx.input.setInputProcessor(screen.getInputProcessor());
		
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
