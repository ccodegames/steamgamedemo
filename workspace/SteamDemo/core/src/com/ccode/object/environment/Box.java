package com.ccode.object.environment;

/**
 * A basic box. Should be subclassed to be more specific.
 * 
 * @author gabemac
 * 
 */

public class Box {
	private int width = 2;
	private int height = 2;
	private int weight = 3;
	
	public Box(int width, int height, int weight){
		this.width = width;
		this.height = height;
		this.weight = weight;
	}
	
	public Box(){
		
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWeight(){
		return weight;
	}
}
