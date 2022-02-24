package unamedEngine.components;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import unamedEngine.util.Debug;
import unamedEngine.util.Renderer;

public class QuadRenderer extends Component {
	public int displayList = -1;

	public QuadRenderer() {
		super();
		this.Set("QuadRenderer");
	}

	public void Run() {
		Transform transform = (Transform) this.GameObject.GetComponent("Transform");
		if (transform != null) {
			Material material = (Material) this.GameObject.GetComponent("Material");
			if (material != null) {
				Renderer.renderQuad(new Rectangle(transform.GetPosition().x, transform.GetPosition().y,
						transform.GetBounds().width, transform.GetBounds().height), material.GetColor());
			}
		}
	}
}
