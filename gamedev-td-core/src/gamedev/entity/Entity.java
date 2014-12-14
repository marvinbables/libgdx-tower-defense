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
	
	public void update(float delta){
		sprite.setX(this.position.x);
		sprite.setY(this.position.y);
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
	
	public void setPosition(Vector2 position){
		this.position = position;
		sprite.setX(position.x);
		sprite.setY(position.y);
	}
	
	
	public Vector2 getPosition() {
		return position;
	}
}
