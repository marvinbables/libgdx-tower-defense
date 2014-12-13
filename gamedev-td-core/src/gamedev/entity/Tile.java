package gamedev.entity;

import gamedev.td.GDSprite;
import gamedev.td.SpriteManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile extends Entity {
	
	public enum TileType {
		Null, Grass, Dirt, Dirt_Dark, Steve
	}

	public static Tile create(TileType type){
		GDSprite tile = SpriteManager.getInstance().getTile(type);
		Tile t = new Tile(tile);
		return t;
	}
	
	public Tile(GDSprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		
	}

	public static TileType interpretType(int val) {
		switch(val){
		case -1: return TileType.Null;
		case  0: return TileType.Grass;
		case  1: return TileType.Dirt;
		case  2: return TileType.Dirt_Dark;
		case  3: return TileType.Steve;
		}
		return TileType.Null;
	}

}