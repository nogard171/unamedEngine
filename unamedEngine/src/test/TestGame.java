package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector2f;

import unamedEngine.components.*;
import unamedEngine.core.*;
import unamedEngine.core.ui.Action;
import unamedEngine.core.ui.Button;
import unamedEngine.util.FPS;
import unamedEngine.util.Renderer;

public class TestGame extends GameLoop {

	GameObject test;

	@Override
	public void Initilize() {
		unamedEngine.databank.Window._Enables.add(GL11.GL_BLEND);
		unamedEngine.databank.Window._Enables.add(GL11.GL_TEXTURE_2D);
		unamedEngine.databank.Window._EnableAlpha = true;
		super.Initilize();

		test = new GameObject("test");
		Transform transform = new Transform(100, 100, 100, 100);
		test.Add(transform);
		Material mat = new Material();
		mat.Set(new Color(1f, 0, 0, 0.5f));
		test.Add(mat);
		QuadRenderer quadRenderer = new QuadRenderer();
		test.Add(quadRenderer);

		btn = new Button("test", new Rectangle(50, 50, 50, 50), new Action() {
			@Override
			public void MouseDown(Button btn) {
				btn.setColor(Color.pink);
			}

			@Override
			public void MouseRelease(Button btn) {
				btn.setColor(Color.green);
			}

			@Override
			public void MouseEnter(Button btn) {
				btn.setColor(Color.green);
			}

			@Override
			public void MouseExit(Button btn) {
				btn.setColor(Color.black);
			}
		});
	}

	@Override
	public void Update() {
		unamedEngine.util.Debug.SetText("FPS", FPS.Get());
		unamedEngine.util.Debug.SetText("Font Count", unamedEngine.databank.Renderer.fontsRendered);
		unamedEngine.util.Debug.SetText("Quad Count", unamedEngine.databank.Renderer.quadsRendered);
		unamedEngine.util.Debug.SetText("Sprite Count", unamedEngine.databank.Renderer.spritesRendered);
		unamedEngine.util.Debug.SetText("SpritesOptimized", unamedEngine.databank.Renderer.spritesOptimized);
		super.Update();
		btn.Update();
	}

	Button btn;

	@Override
	public void Render() {
		super.Render();
		test.GetComponent("QuadRenderer").Run();

		Renderer.renderText(100, 100, "test", Color.white, 12, Font.PLAIN);

		// Renderer.renderSprite("Assets/Tiles/grass.png", new Vector2f(0, 0),
		// Color.white);
		btn.Render();
	}

	@Override
	public void Destroy() {
		super.Destroy();
		System.out.close();
	}

}
