package unamedEngine.util;

import java.awt.Point;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	private static int[] buttons;
	private static int[] buttonCount;
	private static boolean[] buttonDown;

	private static int[] keysCount;
	private static boolean[] keysDown;

	public static Point mousePoint;
	public static boolean moved = false;
	private static Point previousMousePoint = new Point(-1, -1);

	public static void Setup() {
		mousePoint = new Point(0, 0);
		int bCount = Mouse.getButtonCount();
		buttons = new int[bCount];
		buttonCount = new int[bCount];
		buttonDown = new boolean[bCount];
		for (int b = 0; b < bCount; b++) {
			buttons[b] = b;
			buttonCount[b] = 0;
		}

		keysCount = new int[256];
		keysDown = new boolean[256];
		for (int k = 0; k < 256; k++) {
			keysCount[k] = 0;
			keysDown[k] = false;
		}
	}

	public static int[] getKey() {
		int[] key = new int[2];
		for (int i = 0; i < key.length; i++) {
			for (int k = 0; k < 256; k++) {
				if (keysDown[k] && key[0] != k) {
					if (keysCount[k] == 0) {
						key[i] = k;
					}
				}
			}
		}
		return key;
	}

	public static void Update() {
		if (buttons == null || keysDown == null) {
			Setup();
		}
		moved = (previousMousePoint.x != mousePoint.x && previousMousePoint.y != mousePoint.y);
		for (int b = 0; b < buttons.length; b++) {
			buttonCount[b] = (buttonDown[b] == true ? 1 : 0);
			buttonDown[b] = Mouse.isButtonDown(buttons[b]);
		}

		for (int k = 0; k < 256; k++) {
			keysCount[k] = (keysDown[k] == true ? 1 : 0);
			keysDown[k] = Keyboard.isKeyDown(k);
		}
	}

	public static boolean isKeyPressed(int index) {
		boolean isPressed = (keysDown[index] == true && keysCount[index] == 0 ? true : false);
		return isPressed;
	}

	public static boolean isKeyDown(int index) {
		return keysDown[index];
	}

	public static boolean isMousePressed(int index) {
		boolean isPressed = (buttonDown[index] == true && buttonCount[index] == 0 ? true : false);
		return isPressed;
	}

	public static boolean isMouseDown(int index) {
		return buttonDown[index];
	}

	public static Point getMousePoint() {
		mousePoint = new Point(Mouse.getX(), unamedEngine.databank.Window._Height - Mouse.getY());
		if (moved) {

			previousMousePoint = mousePoint;
		}
		return mousePoint;
	}
}
