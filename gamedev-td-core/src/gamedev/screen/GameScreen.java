package gamedev.screen;

import gamedev.entity.GameState;
import gamedev.input.GameInputProcessor;
import gamedev.td.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends GDScreen {
	OrthographicCamera camera;
	
	TowerDefense towerDefense;
	
	SpriteBatch spriteBatch;
	
	GameOverScreen gameOverScreen;
	private GameState gameState;
	
	public GameScreen(TowerDefense towerDefense) {
		this.towerDefense = towerDefense;
		gameOverScreen = towerDefense.getGameOverScreen();
		gameState = GameState.getInstance();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

		gameState.initialize();
		gameState.setMap(1);
		
		inputProcessor = new GameInputProcessor(towerDefense);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
		

		if(GameState.getInstance().getPlayerLife() <= 0)
			towerDefense.switchScreen(towerDefense.getGameOverScreen());
		
		gameState.render(spriteBatch);		
		
		GameUserInterface userInterface = ((GameInputProcessor)inputProcessor).getUserInterface();
		userInterface.draw(spriteBatch);
		update(delta);
	}


	private void update(float delta) {
		gameState.update(delta);
	}

	

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		// TODO: Probably save the game state.
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
