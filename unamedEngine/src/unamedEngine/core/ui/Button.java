package unamedEngine.core.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import unamedEngine.util.Renderer;

public class Button {
	private String value = "";
	private Rectangle bounds;
	private Action action;
	private boolean isHovered = false;
	private int hoverCount = 0;
	private int clickCount = 0;
	private int releaseCount = 0;
	private Color color = Color.black;

	public Button(String newValue, Rectangle newBounds, Action newAction) {
		this.value = newValue;
		this.bounds = newBounds;
		this.action = newAction;
	}

	public void Update() {
		this.isHovered = (bounds.contains(unamedEngine.util.Input.getMousePoint()));
		boolean leftMouseDown = unamedEngine.util.Input.isMouseDown(0);
		boolean leftMousePressed = unamedEngine.util.Input.isMousePressed(0);
		if (this.isHovered && hoverCount <= 0) {
			if (action != null) {
				action.MouseEnter(this);
				hoverCount++;
			}
		}
		if (!this.isHovered && hoverCount > 0) {
			if (action != null) {
				action.MouseExit(this);
				hoverCount = 0;
			}
		}
		if (this.isHovered && leftMousePressed) {
			if (action != null) {
				action.MouseClick(this);
				releaseCount = 0;
			}
		}
		if (this.isHovered && leftMouseDown) {
			if (action != null) {
				action.MouseDown(this);
				releaseCount = 0;
				clickCount++;
			}
		}
		if (releaseCount == 0 && clickCount > 0 && !leftMouseDown) {
			if (action != null) {
				action.MouseRelease(this);
				releaseCount++;
				clickCount = 0;
			}
		}
	}

	public void Render() {
		Renderer.renderQuad(this.bounds, color);
		Renderer.renderText(this.bounds.x, this.bounds.y, this.value, Color.white, 12, Font.PLAIN);
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
