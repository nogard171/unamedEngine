package unamedEngine.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Renderer {
	public static void Update() {
		unamedEngine.databank.Renderer.fontsRendered = 0;
		unamedEngine.databank.Renderer.quadsRendered = 0;
		unamedEngine.databank.Renderer.spritesRendered = 0;
	}

	public static void IncreaseQuadRendered() {
		unamedEngine.databank.Renderer.quadsRendered++;
	}

	public static void renderSprite(String spriteName, Vector2f position, Color color) {

		String key = spriteName + "/" + position + "/" + color;
		Integer count = unamedEngine.databank.Renderer.spritesRenderedList.get(key);
		if (count == null) {
			count = 0;
		}
		if (count < 1) {

			Texture texture = unamedEngine.databank.Renderer.spriteTextures.get(key);
			if (texture == null) {
				try {

					texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(spriteName));
					if (texture != null) {
						unamedEngine.databank.Renderer.spriteTextures.put(key, texture);
					} else {
						unamedEngine.util.Debug.log("Faield to load:" + spriteName);
					}
				} catch (

				IOException e) {
					unamedEngine.util.Debug.log(e.getMessage());
				}
			}
			if (texture != null) {
				Integer displayList = unamedEngine.databank.Renderer.spriteDisplayLists.get(key);
				if (displayList == null) {

					displayList = GL11.glGenLists(1);
					GL11.glNewList(displayList, GL11.GL_COMPILE);
					float r = (color.getRed() == 0 ? 0 : (float) color.getRed() / (float) 255);
					float g = (color.getGreen() == 0 ? 0 : (float) color.getGreen() / (float) 255);
					float b = (color.getBlue() == 0 ? 0 : (float) color.getBlue() / (float) 255);
					float a = (color.getAlpha() == 0 ? 0 : (float) color.getAlpha() / (float) 255);
					GL11.glColor4f(r, g, b, a);

					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0, 0);
					GL11.glVertex2f(position.x, position.y);
					GL11.glTexCoord2f(1, 0);
					GL11.glVertex2f(position.x + texture.getImageWidth(), position.y);
					GL11.glTexCoord2f(1, 1);
					GL11.glVertex2f(position.x + texture.getImageWidth(), position.y + texture.getImageHeight());
					GL11.glTexCoord2f(0, 1);
					GL11.glVertex2f(position.x, position.y + texture.getImageHeight());
					GL11.glEnd();
					GL11.glEndList();
					unamedEngine.databank.Renderer.spriteDisplayLists.put(key, displayList);
					System.out.println("Build");

				}
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
				if (displayList != null) {
					callDisplay(displayList);

					unamedEngine.databank.Renderer.spritesRenderedList.put(key, (int) (count + 1));
				}
				unamedEngine.databank.Renderer.spritesRendered++;
			}
		} else {
			unamedEngine.databank.Renderer.spritesOptimized++;
		}
	}

	public static void renderQuad(Rectangle bound, Color color) {
		if (bound != null && color != null) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			String key = bound + "/" + color;
			Integer displayList = unamedEngine.databank.Renderer.quadDisplayLists.get(key);
			if (displayList == null) {
				displayList = GL11.glGenLists(1);
				GL11.glNewList(displayList, GL11.GL_COMPILE);
				float r = (color.getRed() == 0 ? 0 : (float) color.getRed() / (float) 255);
				float g = (color.getGreen() == 0 ? 0 : (float) color.getGreen() / (float) 255);
				float b = (color.getBlue() == 0 ? 0 : (float) color.getBlue() / (float) 255);
				float a = (color.getAlpha() == 0 ? 0 : (float) color.getAlpha() / (float) 255);
				GL11.glColor4f(r, g, b, a);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(bound.x, bound.y);
				GL11.glVertex2f(bound.x + bound.width, bound.y);
				GL11.glVertex2f(bound.x + bound.width, bound.y + bound.height);
				GL11.glVertex2f(bound.x, bound.y + bound.height);
				GL11.glEnd();
				GL11.glEndList();
				unamedEngine.databank.Renderer.quadDisplayLists.put(key, displayList);
				System.out.println("Build");
			}

			if (displayList != null) {
				callDisplay(displayList);
			}

			unamedEngine.databank.Renderer.quadsRendered++;

			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}

	public static int renderText(float x, float y, String text, Color color, int fontSize, int fontType) {
		int fontWidth = 0;
		String key = fontSize + "," + fontType + "," + color;
		TrueTypeFont font = unamedEngine.databank.Renderer.fonts.get(key);
		if (font == null) {
			Font awtFont = new Font("Fixedsys", fontType, fontSize);
			unamedEngine.databank.Renderer.fonts.put(key, new TrueTypeFont(awtFont, false));
		}
		if (font != null) {
			TextureImpl.bindNone();
			float r = (color.getRed() == 0 ? 0 : (float) color.getRed() / (float) 255);
			float g = (color.getGreen() == 0 ? 0 : (float) color.getGreen() / (float) 255);
			float b = (color.getBlue() == 0 ? 0 : (float) color.getBlue() / (float) 255);
			float a = (color.getAlpha() == 0 ? 0 : (float) color.getAlpha() / (float) 255);
			org.newdawn.slick.Color c = new org.newdawn.slick.Color(r, g, b, a);
			font.drawString(x, y, text, c);
			fontWidth = font.getWidth(text);
			unamedEngine.databank.Renderer.fontsRendered++;
		}
		return fontWidth;
	}

	public static void callDisplay(int displayList) {
		GL11.glCallList(displayList);
	}
}
