package gamedev.entity;

import gamedev.screen.GameScreen;
import gamedev.td.GDSprite;

import com.badlogic.gdx.graphics.Texture;

public class SpriteFactory {
	
	public static GDSprite createSpriteTile(String name){
		GDSprite sprite = null;
		
		Texture texture = TextureFactory.createTexture(name);
		sprite = new GDSprite(texture);
		
		sprite.setBounds(-50, -50, GameScreen.tileSize, GameScreen.tileSize);
		sprite.setFlip(false, true);
		return sprite;
	}
	
	public static GDSprite createSprite(String name){
		GDSprite sprite = null;
		
		Texture texture = TextureFactory.createTexture(name);
		sprite = new GDSprite(texture);
		sprite.setPosition(-50, -50);
		
		sprite.setFlip(false, true);
		return sprite;
	}

	

}
