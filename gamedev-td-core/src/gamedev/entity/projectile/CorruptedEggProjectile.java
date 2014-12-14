package gamedev.entity.projectile;

import gamedev.entity.Enemy;
import gamedev.td.GDSprite;

import com.badlogic.gdx.math.Vector2;

public class CorruptedEggProjectile extends EggProjectile {

	public CorruptedEggProjectile(GDSprite sprite, Vector2 position,
			int damage, float speed, Enemy target) {
		super(sprite, position, damage, speed, target);
		// TODO Auto-generated constructor stub
	}

}
