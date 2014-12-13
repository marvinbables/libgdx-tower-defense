package gamedev.screen;

import gamedev.input.GDInputProcessor;
import gamedev.td.TowerDefense;

import com.badlogic.gdx.Screen;

public abstract class GDScreen implements Screen{
	protected GDInputProcessor inputProcessor;
	protected TowerDefense towerDefense;
	
	public GDScreen(TowerDefense towerDefense) {
		this.towerDefense = towerDefense;
	}

	public GDInputProcessor getInputProcessor() {
		return inputProcessor;
	}
	

}
