package com.bigbass1997.fractaltree.fonts;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontManager {
	
	private static Hashtable<String, Font> fonts;
	
	/**
	 * @param path Internal file path for font.
	 * @param sizes Can be null. Sizes you want generated for the font.
	 */
	public static void addFont(String path, int[] sizes){
		if(fonts == null) fonts = new Hashtable<String, Font>();
		if(sizes == null || sizes.length == 0) sizes = new int[]{12, 16, 24, 32, 48};
		
		FileHandle file = Gdx.files.internal(path);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		for(int i = 0; i < sizes.length; i++){
			parameter.size = sizes[i];
			fonts.put(new FontID(path, sizes[i]).toString(), new Font(generator.generateFont(parameter), sizes[i]));
		}
		generator.dispose();
	}
	public static void addFont(String path){ addFont(path, null); }
	
	public static Font getFont(FontID fontid){
		Font font = null;
		
		font = fonts.get(fontid.toString());
		
		if(font == null){
			addFont(fontid.name, new int[]{fontid.size});
			return getFont(fontid);
		}
		
		return font;
	}
	
	public static Font getFont(String path, int size){
		return getFont(new FontID(path, size));
	}
}
