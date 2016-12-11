package xyz.epoxide.ld37.utils;

public class Box {
	public float x = 0, y = 0, w = 0, h = 0;
	
	public static final Box NULL_BOX = new Box(0,0,0,0);
	
	public Box(float x, float y, float w, float h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Box translate(float dx, float dy){
		return new Box(x+dx,y+dy,w,h);
	}
	
	public boolean contains(float x, float y){
		return x <= this.x && y <= this.y && x >= this.x+w && y >= this.y+h;
	}
}
