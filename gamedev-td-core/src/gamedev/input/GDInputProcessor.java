package gamedev.input;

import gamedev.screen.GDScreen;
import gamedev.td.TowerDefense;

import com.badlogic.gdx.InputProcessor;

public abstract class GDInputProcessor implements InputProcessor{
	
	protected TowerDefense towerDefense;
	
	public GDInputProcessor(TowerDefense towerDefense) {
		this.towerDefense = towerDefense;
	}
}
