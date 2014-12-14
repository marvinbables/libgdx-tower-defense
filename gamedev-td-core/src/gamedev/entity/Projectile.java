package gamedev.entity;

import gamedev.entity.TowerFactory.TowerType;
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
import com.badlogic.gdx.math.Rectangle;
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
		sprite.setRotation(angle);
		sprite.draw(spriteBatch);
	}
	
	
	//TODO implement method update()
	public void update(float delta){
		super.update(delta);
		boolean collided = checkCollision(target);
		if(collided){
			target.damagedBySource(this.damage);
			this.active = false;
		}
		else{
			position.x += Math.cos(angle);
			position.y += Math.sin(angle);
			
		}
	}
	
	//TODO check enemy collision
	private boolean checkCollision(Enemy target) {
		Rectangle minRect = sprite.getBoundingRectangle();
		return minRect.contains(target.getSprite().getBoundingRectangle());
		
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
	
	public static ProjectileType interpretTypeFromTowerName(String name) {
		if(name.equals("Dirt Tower"))
			return ProjectileType.Dirt;
		else if(name.equals("Arrow Tower"))
			return ProjectileType.Arrow;
		else if(name.equals("Egg Tower"))
			return ProjectileType.Egg;
		else if(name.equals("Potion Tower"))
			return ProjectileType.Potion;
		else if(name.equals("Corrupted Egg Tower"))
			return ProjectileType.Cegg;
		else if(name.equals("Ice Arrow Tower"))
			return ProjectileType.Ice_Arrow;
		else if(name.equals("Fire Arrow Tower"))
			return ProjectileType.Fire_Arrow;
		return null; 
	}
	
	private float getAngle(){
		Vector2 targetCenter = MathHelper.getCenterOfTile(target.getPosition());
		
		float deltaX = (float)Math.abs(this.position.x - targetCenter.x);
		float deltaY = (float)Math.abs(this.position.y - targetCenter.y);
		float angleInDegrees = (float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI);
		
		return angleInDegrees;
		
	}
	
}
