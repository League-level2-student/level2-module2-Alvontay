package _04_animals_inheritance;

import java.awt.Color;

public class Animal {
	
	String name;
	String color;
	boolean idk;
	
	public Animal(String name, String color, boolean idk) {
		this.name = name;
		this.color = color;
		this.idk = idk;
	}
	
	public void printName() {
		System.out.println(name);
	}
	
	public void play() {
		System.out.println(name + " is playing");
	}
	
	public void eat() {
		System.out.println(name + " is eating");
	}
	
	public void sleep() {
		System.out.println(name + " is sleeping");
	}
}
