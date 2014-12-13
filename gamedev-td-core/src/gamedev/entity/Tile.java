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

}
