package gamedev.screen;

import gamedev.input.LvlSelectInputProcessor;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;
import gamedev.td.TowerDefense;

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

public class LvlSelectScreen extends GDScreen{

	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	BitmapFont font;
	private List<GDSprite> buttons;
	public final static int MAP1 = 1, MAP2 = 2, MAP3 = 3, MAIN_MENU = 0;
	GDSprite menuBtn, map1Btn, map2Btn, map3Btn, background;
	
	public LvlSelectScreen(TowerDefense towerDefense) {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		
		spriteBatch = new SpriteBatch();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		initializeFont();
		initializeButtons();
		this.inputProcessor = new LvlSelectInputProcessor(towerDefense, this);
	}
	
	private void initializeButtons() {
		buttons = new ArrayList<GDSprite>();
		SpriteManager spriteManager = SpriteManager.getInstance();
		
		menuBtn = spriteManager.getSprite("back_to_menu_button");
		menuBtn.setPosition(160,520);
		
		map1Btn = spriteManager.getSprite("map1");
		map1Btn.setPosition(80, 180);
		map1Btn.setAlpha(0.7f);
		
		map2Btn = spriteManager.getSprite("map2");
		map2Btn.setPosition(400, 180);
		map2Btn.setAlpha(0.7f);
		
		map3Btn = spriteManager.getSprite("map3");
		map3Btn.setPosition(240, 360);
		map3Btn.setAlpha(0.7f);
		
		buttons.add(menuBtn);
		buttons.add(map1Btn);
		buttons.add(map2Btn);
		buttons.add(map3Btn);
		
		background = spriteManager.getSprite("lvlselectbg");
		background.setPosition(0, 0);
		
	}
	
	private void initializeFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Minecraftia.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.flip = true;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
	
	public List<GDSprite> getButtons() {
		return buttons;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		spriteBatch.begin();
		background.draw(spriteBatch);
			for(GDSprite button : buttons) {
				button.draw(spriteBatch);
			}
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
