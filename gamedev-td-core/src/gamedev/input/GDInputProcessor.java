package gamedev.input;

import gamedev.screen.GDScreen;
import gamedev.td.TowerDefense;

import com.badlogic.gdx.InputProcessor;

public abstract class GDInputProcessor implements InputProcessor{
	
	protected TowerDefense towerDefense;
	protected GDScreen screen;
	
	public void initialize(GDScreen screen){
		this.screen = screen;
	}
}
