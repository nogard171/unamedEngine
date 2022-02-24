package unamedEngine.components;

import java.awt.Color;

public class Material extends Component {
	private Color color = Color.white;
	private boolean updated = false;

	public Material() {
		super();
		this.Set("Material");
	}

	public Color GetColor() {
		return color;
	}

	public void Set(Color color) {
		if (this.color != null) {
			if (!this.color.equals(color)) {
				updated = true;
			}
		}
		this.color = color;
	}

	public boolean Updated() {
		return this.updated;
	}
}
