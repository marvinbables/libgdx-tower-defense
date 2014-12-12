package gamedev.entity;

import gamedev.td.GDSprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

	protected float x, y;
	protected GDSprite sprite;
	protected boolean active;
	
	public abstract void draw(SpriteBatch spriteBatch);
	public abstract void update();
	
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
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
