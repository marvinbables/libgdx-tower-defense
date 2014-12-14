package gamedev.td.helper;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;

public class MathHelper {
	public static Vector2 PointToVector2(Point p){
		return new Vector2(p.x, p.y);
	}
}
