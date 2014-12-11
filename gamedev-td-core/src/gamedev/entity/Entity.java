package gamedev.entity;

public abstract class Entity {

	protected float x, y;
	
	public abstract void draw();
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
	
	
	
	
}
