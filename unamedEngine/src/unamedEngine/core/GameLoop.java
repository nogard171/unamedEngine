package unamedEngine.core;

import unamedEngine.util.Debug;
import unamedEngine.util.FPS;

public class GameLoop {

	private boolean _isRunning = true;

	public void Start() {
		this.Initilize();
		while (_isRunning) {
			this.Update();
			this.Render();
		}
		this.Destroy();
	}

	public void Initilize() {
		if (!Window.Initilize()) {
			Debug.log("Failed to start Game because of Window Class");
			_isRunning = false;
		}
		unamedEngine.util.Debug.Init();
		unamedEngine.util.Debug.Show();
		unamedEngine.util.Input.Setup();
	}

	public void Update() {
		Window.Update();
		unamedEngine.util.Renderer.Update();
		FPS.Update();
		if (Window.IsClosed()) {
			this.Close();
		}
		if (Window.WasResized()) {
			Debug.Show("Window Resize");
		}
		unamedEngine.util.Input.Update();
	}

	public void Render() {
		Window.Clear();
		unamedEngine.databank.Renderer.spritesRenderedList.clear();
		unamedEngine.databank.Renderer.spritesOptimized = 0;
	}

	public void Destroy() {
		Window.Destroy();
		unamedEngine.util.Debug.Destroy();

	}

	public void Close() {
		_isRunning = false;
	}
}
