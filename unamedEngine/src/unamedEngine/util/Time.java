package unamedEngine.util;

public class Time {
	public static long Get() {
		return System.nanoTime() / 1000000;
	}
}
