package gamedev.screen;

import gamedev.input.MenuInputProcessor;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;
import gamedev.td.TowerDefense;
import gamedev.td.helper.FontHelper;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainMenuScreen extends GDScreen {
	
	OrthographicCamera camera;
	
	SpriteBatch spriteBatch;

	BitmapFont font;
	
	List<GDSprite> buttons;
	
	GDSprite startGameBtn, settingsBtn, aboutBtn, background, title, exitBtn;
	
	public final static int START_GAME = 0, LEVEL_SELECT = 1, ABOUT = 2, EXIT = 3; 
	
	public MainMenuScreen(TowerDefense towerDefense) {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		FontHelper.initialize();
		font = FontHelper.minecraftia12px;
		
		initializeButtons();
		this.inputProcessor = new MenuInputProcessor(towerDefense, this);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		spriteBatch.begin();
			background.draw(spriteBatch);
			title.draw(spriteBatch);
			for(GDSprite button : buttons) {
				button.draw(spriteBatch);
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
	
	private void initializeButtons() {
		buttons = new ArrayList<GDSprite>();
		
		SpriteManager spriteManager = SpriteManager.getInstance();
		
		startGameBtn = spriteManager.getSprite("play_button");
		startGameBtn.setPosition(150, 380);
		
		settingsBtn = spriteManager.getSprite("settings_button");
		settingsBtn.setPosition(150, 420);
		
		aboutBtn = spriteManager.getSprite("about_button");
		aboutBtn.setPosition(150, 460);
		
		exitBtn = spriteManager.getSprite("exit_button");
		exitBtn.setPosition(150, 500);
		
		buttons.add(startGameBtn);
		buttons.add(settingsBtn);
		buttons.add(aboutBtn);
		buttons.add(exitBtn);
		
		background = spriteManager.getSprite("main_background");
		background.setPosition(0, 100);
		
		title = spriteManager.getSprite("title");
		title.setPosition(0, 100);
	}
		
	public List<GDSprite> getButtons() {
		return buttons;
	}
	
	
}
