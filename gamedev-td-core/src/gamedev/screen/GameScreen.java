package gamedev.screen;

import java.util.ArrayList;
import java.util.List;

import gamedev.entity.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	/*
	 * Screen is view (MVC)
	 * 
	 * Display UI
	 * Display score, money, etc
	 * Display enemy textures
	 * Display tower textures
	 * Display map
	 */
	
	GameState gameState; // Model
	
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	List<Sprite> tiles;
	final int tileSize = 40;
	
	float sec = 0, time = 0; // test
	
	public GameScreen() {
		gameState = new GameState();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		tiles = new ArrayList<Sprite>();
		
		initializeSprites();
		gameState.prepareLevel(1);
	}
	
	private void initializeSprites() {
		Texture grass = new Texture(Gdx.files.internal("assets/img/grass.png"));
		Texture dirt = new Texture(Gdx.files.internal("assets/img/dirt_light.png"));
		Texture dirtDark = new Texture(Gdx.files.internal("assets/img/dirt_dark.png"));
		
		Sprite grassTile = createTile(grass);
		Sprite dirtTile = createTile(dirt);
		Sprite dirtDarkTile = createTile(dirtDark);
		
		tiles.add(grassTile);
		tiles.add(dirtTile);
		tiles.add(dirtDarkTile);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sec += delta;
		if(sec >= 1) {
			time++;
//			System.out.println(time);
			sec -= 1;
		}
		spriteBatch.begin();
			int grid[][] = gameState.getGrid();
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					getSprite(grid[i][j]).setPosition(i*tileSize, j*tileSize);
					getSprite(grid[i][j]).draw(spriteBatch);
				}
			}
		spriteBatch.end();
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
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	public GameState getGameState() {
		return gameState;
	}
	
	// Factory
	private Sprite createTile(Texture texture) {
		Sprite sprite = new Sprite(texture);
		// tile size 40x40
		sprite.setBounds(-10, -10, tileSize, tileSize);
		sprite.flip(false, true);
		return sprite;
	}
	
	private Sprite getSprite(int correspondingNumber) {
		if(correspondingNumber >= tiles.size())
			return tiles.get(0);
		
		switch(correspondingNumber) {
			case 0: return tiles.get(0);
			case 1: return tiles.get(1);
			case 2: return tiles.get(2);
			default: return tiles.get(0);
		}
	}

}
