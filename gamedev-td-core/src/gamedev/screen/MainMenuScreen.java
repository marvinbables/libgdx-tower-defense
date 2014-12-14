package gamedev.screen;

import gamedev.input.MenuInputProcessor;
import gamedev.td.GDSprite;
import gamedev.td.TowerDefense;
import gamedev.td.helper.FontHelper;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class MainMenuScreen extends GDScreen {

	TowerDefense towerDefense;
	
	OrthographicCamera camera;
	
	SpriteBatch spriteBatch;;

	BitmapFont font;
	
	List<GDSprite> buttons, highlightedButtons;
	
	GDSprite startGameBtn, lvlSelectBtn, aboutBtn;
	
	public final static int START_GAME = 0, LEVEL_SELECT = 1, ABOUT = 2; 
	
	public MainMenuScreen(TowerDefense towerDefense) {
		super(towerDefense);
		this.towerDefense = towerDefense;
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
		
		Texture startGameBtnTx = new Texture(Gdx.files.internal("assets/img/play_button.png"));
		Texture lvlSelectBtnTx = new Texture(Gdx.files.internal("assets/img/settings_button.png"));
		Texture aboutBtnTx = new Texture(Gdx.files.internal("assets/img/about_button.png"));
		
		startGameBtn = new GDSprite(startGameBtnTx);
		startGameBtn.setPosition(0, 0);
		startGameBtn.flip(false, true);
		lvlSelectBtn = new GDSprite(lvlSelectBtnTx);
		lvlSelectBtn.setPosition(0, 40);
		lvlSelectBtn.flip(false, true);
		aboutBtn = new GDSprite(aboutBtnTx);
		aboutBtn.flip(false, true);
		aboutBtn.setPosition(0, 80);
		
		
		buttons.add(startGameBtn);
		buttons.add(lvlSelectBtn);
		buttons.add(aboutBtn);
		
	}
		
	public List<GDSprite> getButtons() {
		return buttons;
	}
	
	
}
