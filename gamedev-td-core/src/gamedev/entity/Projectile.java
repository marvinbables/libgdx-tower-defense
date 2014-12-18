package gamedev.entity;

import gamedev.entity.projectile.ArrowProjectile;
import gamedev.entity.projectile.CorruptedEggProjectile;
import gamedev.entity.projectile.DirtProjectile;
import gamedev.entity.projectile.EggProjectile;
import gamedev.entity.projectile.FireArrowProjectile;
import gamedev.entity.projectile.IceArrowProjectile;
import gamedev.entity.projectile.PotionProjectile;
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
	protected float speed, angle, slowDuration;
	protected Enemy target;
	
	public Projectile(GDSprite sprite, Vector2 position, int damage, float speed, Enemy target){
		super(sprite);
		this.position = position;
		this.damage = damage;
		this.speed = speed;
		this.target = target;
		this.angle = getAngle();
		slowDuration = 0;
		active = true;
	}
	
	public void draw(SpriteBatch spriteBatch){
		if(active){
			sprite.setRotation(angle);
			sprite.draw(spriteBatch);
		}
	}
	
	
	public void update(float delta){
		if(active){
			super.update(delta);
			angle = getAngle();
			if(!target.active)
				active = false;
			boolean collided = false;
			GameState state = GameState.getInstance();
			for(Enemy enemy : state.getEnemies()){
				if(enemy.active){
					collided = checkCollision(enemy);
					if(collided && active){
						enemy.damagedBySource(this.damage);
						enemy.slowedBySource(this.slowDuration);
						this.active = false;
					}
				}
			}
			if(!collided){
				moveProjectile(target);
				
			}
		}
	}
	
	private void moveProjectile(Enemy target) {
		Vector2 enemyPosition = MathHelper.getCenterOfTile(target.position);
		Vector2 center = MathHelper.getCenterOfSprite(sprite);
		if(enemyPosition.x > center.x){
			position.x += speed;
		}
		else if (enemyPosition.x < center.x){
			position.x -= speed;
		}
		if(enemyPosition.y > center.y){
			position.y += speed;
		}
		else if (enemyPosition.y < center.y){
			position.y -= speed;
		}
	}

	private boolean checkCollision(Enemy target) {
		Rectangle minRect = sprite.getBoundingRectangle();
		return minRect.overlaps(target.getSprite().getBoundingRectangle());
		
	}

	public static Projectile createProjectile(Tower tower, ProjectileType type, Enemy target){
		Projectile projectile = null;
		SpriteManager handler = SpriteManager.getInstance();
		GDSprite projectileSprite = handler.getProjectile(type);
		Vector2 position = new Vector2(tower.getPosition());
		int damage = tower.getDamage();
		float speed;
		
		switch(type){
			case Dirt:
				speed = 3f;
				return new DirtProjectile(projectileSprite, position, damage, speed, target);
			case Arrow:
				speed = 5f;
				return new ArrowProjectile(projectileSprite, position, damage, speed, target);
			case Egg:
				speed = 3f;
				return new EggProjectile(projectileSprite, position, damage, speed, target);
			case Potion:
				speed = 3f;
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
	
	protected float getAngle(){
		Vector2 targetCenter = MathHelper.getCenterOfTile(target.getPosition());
		
		float deltaX = (float)(this.position.x - targetCenter.x);
		float deltaY = (float)(this.position.y - targetCenter.y);
		float angleInDegrees = (float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI);
		
		return angleInDegrees;
		
	}
	
}
