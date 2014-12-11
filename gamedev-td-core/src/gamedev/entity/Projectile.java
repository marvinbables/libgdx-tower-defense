package gamedev.entity;

public abstract class Projectile {
	
	private float speed;
	
	
	//TODO implement method draw()
	public void draw(){
		
	}
	
	
	//TODO implement method update()
	public void update(){
		
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
	
	
}
