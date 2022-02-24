package unamedEngine.util;

public class FPS {
	static long lastFPS = -1;
	static long lastFrame;
	static int oldFPS;
	public static int fps = 0;
	public static float currentDelta = 0;

	public static long Get() {
		return fps;
	}

	public static float GetDelta() {
		long time = Time.Get();
		float delta = (float) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private static void Start() {
		lastFPS = Time.Get();
	}

	public static void Update() {
		if (lastFPS == -1) {
			Start();
		}
		if (Time.Get() - lastFPS > 1000) {
			fps = oldFPS;
			oldFPS = 0;
			lastFPS += 1000;
		}
		oldFPS++;
		currentDelta = GetDelta();
	}

}
