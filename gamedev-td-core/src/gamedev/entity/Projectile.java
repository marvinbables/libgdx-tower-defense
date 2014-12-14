package gamedev.entity;

import gamedev.entity.projectile.ArrowProjectile;
import gamedev.entity.projectile.CorruptedEggProjectile;
import gamedev.entity.projectile.DirtProjectile;
import gamedev.entity.projectile.EggProjectile;
import gamedev.entity.projectile.FireArrowProjectile;
import gamedev.entity.projectile.IceArrowProjectile;
import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;
import gamedev.td.helper.MathHelper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile extends Entity {
	public enum ProjectileType {
		Dirt, Arrow, Egg, Potion, Cegg, Fire_Arrow, Ice_Arrow
	}
	
	private int damage;
	private float speed, angle;
	private Enemy target;
	
	//TODO projectile constructor
	public Projectile(GDSprite sprite, Vector2 position, int damage, float speed, Enemy target){
		super(sprite);
		this.position = position;
		this.damage = damage;
		this.speed = speed;
		this.target = target;
		this.angle = getAngle();
	}
	
	//TODO implement method draw()
	public void draw(SpriteBatch spriteBatch){
		spriteBatch.begin();
		sprite.setRotation(angle);
		sprite.draw(spriteBatch);
		spriteBatch.end();
	}
	
	
	//TODO implement method update()
	public void update(float delta){
		super.update(delta);
		checkCollision(target);
	}
	
	//TODO check enemy collision
	private void checkCollision(Enemy target) {
		
		
	}

	public static Projectile createProjectile(Tower tower, ProjectileType type, Enemy target){
		Projectile projectile = null;
		SpriteManager handler = SpriteManager.getInstance();
		GDSprite projectileSprite = handler.getProjectile(type);
		Vector2 position = MathHelper.getCenterOfTile(tower.getPosition());
		int damage = tower.getDamage();
		float speed;
		
		switch(type){
			case Dirt:
				speed = 4f;
				return new DirtProjectile(projectileSprite, position, damage, speed, target);
			case Arrow:
				speed = 6f;
				return new ArrowProjectile(projectileSprite, position, damage, speed, target);
			case Egg:
				speed = 4f;
				return new EggProjectile(projectileSprite, position, damage, speed, target);
			case Potion:
				speed = 4f;
				return new PotionProjectile(projectileSprite, position, damage, speed, target);
			case Cegg:
				speed = 4f;
				return new CorruptedEggProjectile(projectileSprite, position, damage, speed, target);
			case Fire_Arrow:
				speed = 6f;
				return new FireArrowProjectile(projectileSprite, position, damage, speed, target);
			case Ice_Arrow:
				speed = 6f;
				return new IceArrowProjectile(projectileSprite, position, damage, speed, target);
				
		}
		
		return projectile;
	}
	
	private float getAngle(){
		Vector2 targetCenter = MathHelper.getCenterOfTile(target.getPosition());
		
		float deltaX = (float)Math.abs(this.position.x - targetCenter.x);
		float deltaY = (float)Math.abs(this.position.y - targetCenter.y);
		float angleInDegrees = (float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI);
		
		return angleInDegrees;
		
	}
	
}
