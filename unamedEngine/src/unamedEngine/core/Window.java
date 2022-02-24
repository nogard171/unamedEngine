package unamedEngine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/*
 * AM
 * Feb.2.2022
 * Comment:
 * Initial setup of window class
 */
public class Window {

	public static boolean Initilize() {
		try {
			// Setup the display mode in the databank for global usage if needed.
			unamedEngine.databank.Window._DisplayMode = new DisplayMode(unamedEngine.databank.Window._Width,
					unamedEngine.databank.Window._Height);

			// future fullscreen code

			// Setup the display with the display mode in the databank
			Display.setDisplayMode(unamedEngine.databank.Window._DisplayMode);
			// Set the display to resizable based on the databank
			Display.setResizable(unamedEngine.databank.Window._Resizable);
			// Craete the display for usage
			Display.create();

			// Setup Enables based on databank
			SetupEnables();

			// Setup the Viewport
			SetupViewport();

		} catch (LWJGLException e) {
			// Log any exceptions here to the debug class
			unamedEngine.util.Debug.log(e.getMessage());
			// Return a failure of the method
			return false;
		}
		return true;
	}

	public static boolean Update() {
		if (IsResized()) {
			SetupViewport();
			unamedEngine.databank.Window._WasResized = true;
		}
		Display.update();
		if (!Display.isActive() && unamedEngine.databank.Window._InactiveFPS > -1) {
			Display.sync(unamedEngine.databank.Window._InactiveFPS);
		} else if (Display.isActive() && unamedEngine.databank.Window._Vsync > -1) {
			Display.sync(unamedEngine.databank.Window._Vsync);
		} else {
			Display.sync(unamedEngine.databank.Window._MaxFPS);
		}

		return true;
	}

	public static boolean Clear() {
		unamedEngine.databank.Window._WasResized = false;
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
		return true;
	}

	public static boolean Destroy() {
		Display.destroy();
		return true;
	}

	private static void SetupViewport() {
		unamedEngine.databank.Window._Width = Display.getWidth();
		unamedEngine.databank.Window._Height = Display.getHeight();

		GL11.glViewport(0, 0, unamedEngine.databank.Window._Width, unamedEngine.databank.Window._Height);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, unamedEngine.databank.Window._Width, unamedEngine.databank.Window._Height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	private static void SetupEnables() {
		for (int enable : unamedEngine.databank.Window._Enables) {
			GL11.glEnable(enable);
		}
		if (unamedEngine.databank.Window._EnableAlpha) {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
	}

	private static boolean IsResized() {
		return Display.wasResized();
	}

	public static boolean IsClosed() {
		return Display.isCloseRequested();
	}

	public static boolean WasResized() {
		return unamedEngine.databank.Window._WasResized;
	}
}
