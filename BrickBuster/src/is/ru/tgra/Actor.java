package is.ru.tgra;

import java.util.Random;

import org.lwjgl.util.Point;

public abstract class Actor {
	// a Point object to store coordinates of the Actor
	protected Point point = new Point();
	
	// this application is wacky and implements random colors for each Actor. This should guarantee a seizure
	Random rand = new Random();
	protected float r = rand.nextFloat();
	protected float g = rand.nextFloat();
	protected float b = rand.nextFloat();
	
	public void display(Point p) {
		// this method must be overridden by child class
	}
	
	public void display(float x, float y) {
		// this method must be overridden by child class
	}
	
	public void update() {
		// this method must be overridden by child class
	}
}
