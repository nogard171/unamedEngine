package unamedEngine.databank;

import java.util.HashMap;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Renderer {
	public static int fontsRendered = 0;
	public static int quadsRendered = 0;
	public static int spritesRendered = 0;
	public static int spritesOptimized = 0;

	public static HashMap<String, TrueTypeFont> fonts = new HashMap<String, TrueTypeFont>();
	public static HashMap<String, Integer> quadDisplayLists = new HashMap<String, Integer>();
	public static HashMap<String, Integer> spriteDisplayLists = new HashMap<String, Integer>();
	public static HashMap<String, Texture> spriteTextures = new HashMap<String, Texture>();
	public static HashMap<String, Integer> spritesRenderedList = new HashMap<String, Integer>();
}
