package unamedEngine.databank;

import java.util.LinkedList;

import org.lwjgl.opengl.DisplayMode;

public class Window {
	public static int _Width = 800;
	public static int _Height = 600;
	public static boolean _Resizable = true;
	public static boolean _Fullscreen = false;
	public static int _Vsync = -1;
	public static int _MaxFPS = -1;
	public static int _InactiveFPS = 30;
	public static DisplayMode _DisplayMode;
	

	public static boolean _WasResized = false;

	public static LinkedList<Integer> _Enables = new LinkedList<Integer>();
	public static boolean _EnableAlpha = true;

}
