package gamedev.screen;

import gamedev.entity.GameState;
import gamedev.input.GDInputProcessor;
import gamedev.td.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends GDScreen {
	TowerDefense towerDefense;
	OrthographicCamera camera;

	SpriteBatch spriteBatch;
	
	GameUserInterface userInterface;

	private GameState gameState;
	
	public GameScreen(TowerDefense towerDefense, GDInputProcessor inputProcessor) {
		super(towerDefense);

		gameState = GameState.getInstance();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);

		userInterface = new GameUserInterface();

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

		gameState.initialize();
		gameState.prepareLevel(1);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
		
		update(delta);
		gameState.render(spriteBatch);		
		userInterface.draw(spriteBatch);
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
