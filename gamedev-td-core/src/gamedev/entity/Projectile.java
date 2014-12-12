package gamedev.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Projectile extends Entity {
	
	private int damage;
	private float speed, angle;
	
	//TODO projectile constructor
	public Projectile(){
		
	}
	
	//TODO implement method draw()
	public void draw(SpriteBatch spriteBatch){
		sprite.setX(this.x);
		sprite.setY(this.y);
		sprite.setRotation(angle);
		sprite.draw(spriteBatch);
		
	}
	
	
	//TODO implement method update()
	public void update(Enemy enemy){
		
	}
	
	//TODO implement method createProjectile (factory pattern)
	public static Projectile createProjectile(String type){
		Projectile projectile = null;
		
		return projectile;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}


	public float getAngle() {
		return angle;
	}


	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
}
