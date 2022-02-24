package unamedEngine.core;

import java.util.LinkedList;

import unamedEngine.components.Component;

public class GameObject {
	private LinkedList<Component> components = new LinkedList<Component>();

	private String Name = "";

	public GameObject(String newName) {
		this.Name = newName;
	}

	public void Add(Component newComponent) {
		newComponent.Set(this);
		components.add(newComponent);
	}

	public Component GetComponent(String componentName) {
		Component temp = new Component();
		for (Component c : components) {
			if (c.getName().toLowerCase().equals(componentName.toLowerCase())) {
				temp = c;
				break;
			}
		}
		return temp;
	}

	public String getName() {
		return this.Name;
	}
}
