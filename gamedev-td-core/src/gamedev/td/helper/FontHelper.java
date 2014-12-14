package gamedev.td.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontHelper {
	public static BitmapFont minecraftia14px = null;
	public static BitmapFont minecraftia12px = null;
	public static BitmapFont minecraftia8px = null;

	/**
	 * This method must be called once to register all fonts to be used.
	 */
	public static void initialize() {
		prepareMinecraftiaFont();
	}

	private static void prepareMinecraftiaFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Minecraftia.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.flip = true;
		
		// font size 14 pixels
		parameter.size = 14;
		minecraftia14px = generator.generateFont(parameter); 

		// font size 12 pixels
		parameter.size = 12;
		minecraftia12px = generator.generateFont(parameter);
		
		// font size 8 pixels
		parameter.size = 8;
		minecraftia8px = generator.generateFont(parameter);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
}
