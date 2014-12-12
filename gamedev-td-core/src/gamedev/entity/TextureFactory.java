package gamedev.entity;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static Texture createTexture(String key){
		String path = "";
		key = key.toLowerCase();
		Texture texture = (Texture)textures.get(key);
		if(texture == null){
			if(key.equals("spider")){
				path = "assets/img/spider.png";
			}
			else if(key.equals("grass")){
				path = "assets/img/grass.png";
			}
			else if(key.equals("dirt")){
				path = "assets/img/dirt_light.png";
			}
			else if(key.equals("dirt_dark")){
				path = "assets/img/dirt_dark.png";
			}
			else if(key.equals("steve")){
				path = "assets/img/steve.png";
			}
			else if(key.equals("dirtdark")){
				path = "assets/img/dirt_dark.png";
			}
			else if(key.equals("highlight")){
				path = "assets/img/tile_highlight.png";
			}
			else if(key.equals("ui")){
				path = "assets/img/ui.png";
			}
			else if(key.equals("heart")){
				path = "assets/img/heart.png";
			}
			else if(key.equals("label")){
				path = "assets/img/tower_label.png";
			}
			else if(key.equals("emerald")){
				path = "assets/img/emerald.png";
			}
			else if(key.equals("wave")){
				path = "assets/img/wave.png";
			}
			else if(key.equals("red_highlight")){
				path = "assets/img/red_highlight.png";
			}
			else if(key.equals("dirt_tower")){
				path = "assets/img/new_dirt_tower.png";
			}
			else if(key.equals("arrow_tower")){
				path = "assets/img/new_arrow_tower.png";
			}
			else if(key.equals("egg_tower")){
				path = "assets/img/new_egg_tower.png";
			}
			else if(key.equals("potion_tower")){
				path = "assets/img/new_potion_tower.png";
			}
			else if(key.equals("currency_tower")){
				path = "assets/img/new_currency_tower.png";
			}
			else{
				return null;
			}
			
			
			texture = new Texture(Gdx.files.internal(path));
		}
		
		
		return texture;
	}

}
