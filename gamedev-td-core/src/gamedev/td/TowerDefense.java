package gamedev.td;

import gamedev.screen.GDScreen;
import gamedev.screen.GameOverScreen;
import gamedev.screen.GameScreen;
import gamedev.screen.LvlSelectScreen;
import gamedev.screen.MainMenuScreen;
import gamedev.screen.PauseScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TowerDefense extends Game {

	private MainMenuScreen mainMenuScreen;
	private GameOverScreen gameOverScreen;
	private GameScreen gameScreen;
	private PauseScreen pauseScreen;
	private LvlSelectScreen lvlSelectScreen;
	
	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);
		setLvlSelectScreen(new LvlSelectScreen(this));
		setPauseScreen(new PauseScreen(this));
		
		switchScreen(mainMenuScreen);
		
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

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}

	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}

	public PauseScreen getPauseScreen() {
		return pauseScreen;
	}

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}

	public LvlSelectScreen getLvlSelectScreen() {
		return lvlSelectScreen;
	}

	public void setLvlSelectScreen(LvlSelectScreen lvlSelectScreen) {
		this.lvlSelectScreen = lvlSelectScreen;
	}
}
