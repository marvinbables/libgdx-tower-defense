package gamedev.entity;

import gamedev.td.GDSprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 position;
	protected GDSprite sprite;
	protected boolean active;
	
	public Entity(GDSprite sprite){
		this.sprite = sprite;
		this.active = false;
	}
	
	public abstract void draw(SpriteBatch spriteBatch);
	public abstract void update(float delta);
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public GDSprite getSprite() {
		return sprite;
	}
	
	public void setSprite(GDSprite sprite) {
		this.sprite = sprite;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
