package unamedEngine.components;

import java.awt.Point;
import java.awt.Rectangle;

public class Transform extends Component {
	private Rectangle bounds;
	private boolean updated = false;

	public Transform(int x, int y, int w, int h) {
		this.bounds = new Rectangle(x, y, w, h);
		this.Set("Transform");
	}

	public Rectangle GetBounds() {
		return bounds;
	}

	public Point GetPosition() {
		return new Point(bounds.x, bounds.y);
	}

	public void Set(Point position) {
		if (this.bounds.x != position.x || this.bounds.y != position.y) {
			this.updated = true;
		}
		this.bounds.x = position.x;
		this.bounds.y = position.y;
	}

	public void Set(Rectangle bounds) {
		if (!this.bounds.equals(bounds)) {
			this.updated = true;
		}
		this.bounds = bounds;
	}

	public boolean Updated() {
		return this.updated;
	}
}
