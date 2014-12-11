package gamedev.td;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GDSprite extends Sprite {
	
	public GDSprite(Texture texture) {
		super(texture);
	}

	public Point getPosition(){
		return new Point((int)getX(), (int)getY());
	}

}
