package gamedev.input;

import gamedev.entity.GameState;
import gamedev.entity.Tower;
import gamedev.screen.GameScreen;
import gamedev.td.GDSprite;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;

public class GameInputProcessor extends GDInputProcessor{
		
	private GameScreen gameScreen;
	private Tower towerToPut;

	Color red = new Color(1,0,0,.5f);
	Color white = new Color(1,1,1,.5f);
	
	public GameInputProcessor(GameScreen screen){
		this.gameScreen = screen;
		
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
		List<GDSprite> availableTowers = gameScreen.getAvailableTowers();
		for (int i = 0; i < availableTowers.size(); i++) {
			GDSprite sprite = availableTowers.get(i);
			if(Gdx.input.isButtonPressed(Buttons.LEFT))
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
					towerToPut = gameScreen.getGameState().newTower(i);
					gameScreen.setTowerInfo(towerToPut);
					gameScreen.setTowerToPutSprite(i);
					gameScreen.setTowerInfoSprite(i);
					if(gameScreen.getGameState().enoughMoney(towerToPut)){
						gameScreen.setDrawRadius(towerToPut.getAttackRange());
						gameScreen.cloneSprite(i);
					}
					else {
						gameScreen.setDrawRedHighlight(true);
						towerToPut = null;
						gameScreen.setTowerToPutSprite(-1);
						gameScreen.nullClonedTower();
					}
				}
		}
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			Point point = getGridCoordinate(screenX, screenY);
			if(point.x != -50 && point.y != -50 && towerToPut != null && isPlaceable(point)) {
				gameScreen.getGameState().getGrid()[point.x/40][point.y/40] = -1;
				towerToPut.setX(point.x);
				towerToPut.setY(point.y);
				if(gameScreen.getGameState().enoughMoney(towerToPut)){
					towerToPut.setCenter((float) point.x + gameScreen.getTileSize() / 2, (float) point.y + gameScreen.getTileSize() / 2);
					gameScreen.getGameState().deployTower(towerToPut);
					gameScreen.addDeployedTowerSprite();
					towerToPut = null;
					gameScreen.setTowerInfoSprite(-1);
					gameScreen.setTowerToPutSprite(-1);
					gameScreen.setTowerInfo(null);
				}
				else {
					towerToPut = null;
					gameScreen.setTowerInfoSprite(-1);
					gameScreen.setTowerToPutSprite(-1);
				}
				
			}
		}
		if (Gdx.input.isButtonPressed(Buttons.RIGHT)){
		    gameScreen.nullClonedTower();
		    gameScreen.setTowerInfo(null);
		    towerToPut = null;
		    gameScreen.setTowerInfoSprite(-1);
		    gameScreen.setTowerToPutSprite(-1);
		}
	
		return false;
	}
	
	private Point getGridCoordinate(int screenX, int screenY) {
		Point p = new Point(-50, -50);
		
		for (int i = 0; i < GameState.GRIDX+1; i++) {
			if(screenX <= i*gameScreen.getTileSize()) {
				p.x = (i-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		for (int j = 0; j < GameState.GRIDY+1; j++) {
			if(screenY <= j*gameScreen.getTileSize()) {
				p.y = (j-1)*gameScreen.getTileSize();
				break;
			}
		}
		
		return p;
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

			Point point = getGridCoordinate(screenX, screenY);
			gameScreen.setHighlightCoord(point.x, point.y);
			gameScreen.setClonedTowerSpriteLoc(point.x, point.y);
			
			gameScreen.setDrawRedHighlight(false);
			List<GDSprite> towerSprites = gameScreen.getAvailableTowers();
			for (int i = 0; i < towerSprites.size(); i++) {
				GDSprite sprite = towerSprites.get(i);
				if(screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()
						&& screenY >= sprite.getY() && screenY < sprite.getY() + sprite.getHeight()) {
					// assumes size of sprites of available towers is equal to the size of the available towers model
					Point spritePoint = sprite.getPosition();
					if(towerToPut == null)
						gameScreen.drawTowerInfo(true, spritePoint.x, spritePoint.y, gameScreen.getGameState().getAvailableTowers().get(i));
					else 
						gameScreen.drawTowerInfo(true, spritePoint.x, spritePoint.y, towerToPut);
					gameScreen.setTowerInfoSprite(i);
					break;
				}
				else {
					gameScreen.drawTowerInfo(false, -50, -50, towerToPut);
//					if(towerToPut != null)
//						gameScreen.setTowerInfoSprite(i);
//					else
					if(towerToPut == null)
						gameScreen.setTowerInfoSprite(-1);
						
				}
			}
			
			if(!isPlaceable(point)){
				gameScreen.getTowerRangeRenderer().setColor(1,0,0,.5f);
			}
			else gameScreen.getTowerRangeRenderer().setColor(1,1,1,.5f);
			
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isPlaceable(Point p){
		if(p.x < 0 || p.y < 0 || gameScreen.getGameState().getGrid()[p.x/40][p.y/40] != 0)
			return false;
		else return true;
	}
}
