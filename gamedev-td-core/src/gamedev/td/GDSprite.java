package gamedev.td;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GDSprite extends Sprite {
	
	public GDSprite(Texture texture) {
		super(texture);
	}

	public Point getPosition(){
		return new Point((int)getX(), (int)getY());
	}
	
	public boolean contains(Vector2 point)
	{
		return getBoundingRectangle().contains(point);
	}
	
	public boolean contains(int x, int y) {
		return getBoundingRectangle().contains(x, y);
	}
	
	public GDSprite clone(){
		GDSprite clone = new GDSprite(getTexture());
		clone.set(this);
		return clone;
	}
	
}
