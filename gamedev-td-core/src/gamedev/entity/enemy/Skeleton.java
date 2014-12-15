package gamedev.entity.enemy;

import gamedev.entity.Enemy;
import gamedev.td.Config;
import gamedev.td.GDSprite;

import java.awt.Point;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Skeleton extends Enemy {

	public Skeleton(GDSprite sprite, int health, int moneyReward,
			float speed, List<Point> waypointList) {
		super(sprite, health, moneyReward, speed, waypointList);
		sprite.setSize(50, 50);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(SpriteBatch spriteBatch){
		
		int offset = directionToInt(angle);
		sprite.setRegion(offset, offset, 64, 64);
		sprite.setX(this.position.x);
		sprite.setY(this.position.y);
		if(slowAilmentTimer > 0){
			sprite.setColor(Config.green);
		}
		else{
			sprite.setColor(Config.normal);
		}

		sprite.setFlip(false, true);
		sprite.draw(spriteBatch);
	}
	
	private int directionToInt(float angle){
			if(angle == 0)
				return 384;
			else if(angle == 90)
				return 262;
			else if(angle == 180)
				return 132;
			else if(angle == 270)
				return 0;
			
			return 384;
					

	}

}
