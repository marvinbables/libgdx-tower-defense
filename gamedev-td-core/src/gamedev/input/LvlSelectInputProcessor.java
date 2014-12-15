package gamedev.input;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;

import gamedev.entity.GameState;
import gamedev.screen.GameOverScreen;
import gamedev.screen.MainMenuScreen;
import gamedev.screen.LvlSelectScreen;
import gamedev.td.GDSprite;
import gamedev.td.TowerDefense;

public class LvlSelectInputProcessor extends GDInputProcessor{

	

	List<GDSprite> buttons;
	private LvlSelectScreen lvlSelectScreen;

	public LvlSelectInputProcessor(TowerDefense towerDefense, LvlSelectScreen screen){
		super(towerDefense);
		this.lvlSelectScreen = screen;
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
		
		buttons = lvlSelectScreen.getButtons();
		for (int i = 0; i < buttons.size(); i++) {
			GDSprite sprite = buttons.get(i);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT))
				if(sprite.contains(screenX, screenY)) {
					switch(i) {
					case LvlSelectScreen.MAP1:
						GameState.getInstance().initialize();
						GameState.getInstance().setMap(LvlSelectScreen.MAP1);
						towerDefense.switchScreen(towerDefense.getGameScreen());
						break;
					case LvlSelectScreen.MAIN_MENU:
						towerDefense.switchScreen(towerDefense.getMainMenuScreen());
						break;
					case LvlSelectScreen.MAP2:
						GameState.getInstance().setMap(LvlSelectScreen.MAP2);
						towerDefense.switchScreen(towerDefense.getGameScreen());
						break;
					case LvlSelectScreen.MAP3:
						GameState.getInstance().setMap(LvlSelectScreen.MAP3);
						towerDefense.switchScreen(towerDefense.getGameScreen());
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
		buttons = lvlSelectScreen.getButtons();
		for (int i = 0; i < buttons.size(); i++) {
			GDSprite sprite = buttons.get(i);
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
					
					if(buttons.get(i).equals("menuBtn")){
						buttons.get(i).setAlpha(0.8f);
					}
					else
					buttons.get(i).setAlpha(1);
				}
				else {
					if(buttons.get(i).equals("menuBtn")){
						buttons.get(i).setAlpha(1);
					}
					else
					buttons.get(i).setAlpha(0.7f);
				}
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
