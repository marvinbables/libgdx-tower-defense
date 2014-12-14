package gamedev.input;

import gamedev.screen.PauseScreen;
import gamedev.td.GDSprite;
import gamedev.td.TowerDefense;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;

public class PauseInputProcessor extends GDInputProcessor{

	List<GDSprite> buttons;
	private PauseScreen pauseScreen;

	Color red = new Color(1,0,0,.5f);
	Color white = new Color(1,1,1,.5f);

	public PauseInputProcessor(TowerDefense towerDefense, PauseScreen screen){
		super(towerDefense);
		this.pauseScreen = screen;
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
		
		buttons = pauseScreen.getButtons();
		for (int i = 0; i < buttons.size(); i++) {
			GDSprite sprite = buttons.get(i);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT))
				if(sprite.contains(screenX, screenY)) {
					switch(i) {
					case PauseScreen.RESUME:
						towerDefense.switchScreen(towerDefense.getGameScreen());
						break;
					case PauseScreen.RESTART:
						towerDefense.switchScreen(towerDefense.getGameScreen());
						break;
					case PauseScreen.MAIN_MENU:
						towerDefense.switchScreen(towerDefense.getMainMenuScreen());
						break;
					case PauseScreen.EXIT:
						System.exit(1);
						break;
					}
				}
		}
		
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
		buttons = pauseScreen.getButtons();
		for (int i = 0; i < buttons.size(); i++) {
			GDSprite sprite = buttons.get(i);
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
						
					buttons.get(i).setAlpha(0.8f);
				}
				else buttons.get(i).setAlpha(1);
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

