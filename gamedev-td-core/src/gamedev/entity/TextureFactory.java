package gamedev.entity;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static HashMap<String, Texture> projectiles = new HashMap<String, Texture>();
	
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
			else if(key.equals("tower_label")){
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
			else if(key.equals("tower_highlight")){
				path = "assets/img/ui_tower_highlight.png";
			}
			else if(key.equals("info_bg")) {
				path = "assets/img/info_bg.png";
			}
			else if(key.equals("upgrade_button")) {
				path = "assets/img/upgrade_button.png";
			}
			else if(key.equals("sell_button")) {
				path = "assets/img/sell_button.png";
			}
			else if(key.equals("upgrade_to_cegg")) {
				path = "assets/img/upgrade_to_cegg.png";
			}
			else if(key.equals("upgrade_to_slime")) {
				path = "assets/img/upgrade_to_slime.png";
			}
			else if(key.equals("upgrade_to_wood")) {
				path = "assets/img/upgrade_to_wood.png";
			}
			else if(key.equals("upgrade_to_sand")) {
				path = "assets/img/upgrade_to_sand.png";
			}
			else if(key.equals("upgrade_to_firearrow")) {
				path = "assets/img/upgrade_to_firearrow.png";
			}
			else if(key.equals("upgrade_to_icearrow")) {
				path = "assets/img/upgrade_to_iceArrow.png";
			}
			else if(key.equals("main_background")) {
				path = "assets/img/main_background.png";
			}
			else if(key.equals("play_button")) {
				path = "assets/img/play_button.png";
			}
			else if(key.equals("settings_button")) {
				path = "assets/img/settings_button.png";
			}
			else if(key.equals("about_button")) {
				path = "assets/img/about_button.png";
			}
			else if(key.equals("exit_button")) {
				path = "assets/img/exit_button.png";
			}
			else if(key.equals("restart_button")) {
				path = "assets/img/restart_button.png";
			}
			else if(key.equals("quit2menu_button")) {
				path = "assets/img/quit2menu_button.png";
			}
			else if(key.equals("back_to_menu_button")) {
				path = "assets/img/back_to_menu_button.png";
			}
			else if(key.equals("resume_button")) {
				path = "assets/img/resume_button.png";
			}
			else if(key.equals("gameover")) {
				path = "assets/img/gameover.png";
			}
			else if(key.equals("lvlselectbg")) {
				path = "assets/img/lvlSelectBG.png";
			}
			else if(key.equals("map1")) {
				path = "assets/img/map1.png";
			}
			else if(key.equals("map2")) {
				path = "assets/img/map2.png";
			}
			else if(key.equals("map3")) {
				path = "assets/img/map3.png";
			}
			else if(key.equals("title")) {
				path = "assets/img/title.png";
			}
			else if(key.equals("skeleton")) {
				path = "assets/img/skeleton_spritesheet_reduced.png";
			}
			else if(key.equals("sand_tower")) {
				path = "assets/img/sand_tower.png";
			}
			else if(key.equals("wood_tower")) {
				path = "assets/img/wood_tower.png";
			}
			
			
			
			else{
				return null;
			}
			
			texture = new Texture(Gdx.files.internal(path));
			textures.put(key, texture);
		}
		
		return texture;
	}
	
	public static Texture createProjectile(String key){
		String path = "";
		key.toLowerCase();
		Texture texture = (Texture) projectiles.get(key);
		if(texture == null){
			if(key.equals("arrow")){
				path = "assets/img/projectile_arrow.png";
			}
			else if(key.equals("cegg")){
				path = "assets/img/projectile_cegg.png";
			}
			else if(key.equals("dirt")){
				path = "assets/img/projectile_dirt.png";
			}
			else if(key.equals("egg")){
				path = "assets/img/projectile_egg.png";
			}
			else if(key.equals("fire_arrow")){
				path = "assets/img/projectile_fire_arrow.png";
			}
			else if(key.equals("ice_arrow")){
				path = "assets/img/projectile_ice_arrow.png";
			}
			else if(key.equals("potion")){
				path = "assets/img/projectile_potion.png";
			}
			else if(key.equals("sand")){
				path = "assets/img/projectile_sand.png";
			}
			else if(key.equals("wood")){
				path = "assets/img/projectile_wood.png";
			}
			
			
			else{
				return null;
			}
			
			texture = new Texture(Gdx.files.internal(path));
			projectiles.put(key, texture);
			}
		
		
		
		return texture;
	}

}
