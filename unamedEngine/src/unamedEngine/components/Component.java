package unamedEngine.components;

import unamedEngine.core.GameObject;
import unamedEngine.util.Debug;

public class Component {
	private String Name = "";
	protected GameObject GameObject;

	public Component() {
		this.Name = "Component";
	}

	public void Run() {
		Debug.log("Default Component Run has been triggered");
	}

	public void Set(String newName) {
		this.Name = newName;
	}

	public void Set(GameObject newGameObject) {
		this.GameObject = newGameObject;
	}

	public String getName() {
		return this.Name;
	}

	public GameObject Get() {
		return this.GameObject;
	}
}
