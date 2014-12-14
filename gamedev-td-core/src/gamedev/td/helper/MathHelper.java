package gamedev.td.helper;

import gamedev.td.Config;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;

public class MathHelper {
	public static Vector2 PointToVector2(Point p){
		return new Vector2(p.x, p.y);
	}
	
	public static Point convertToGrid(float x, float y){
		int newX = (int) x / Config.tileSize * Config.tileSize;
		int newY = (int) y / Config.tileSize * Config.tileSize;
		return new Point(newX, newY);
	}
}
