package is.ru.tgra;

public abstract class Actor {
	// a Point object to store coordinates of the Actor
	protected Point point;
	
	// this application is wacky and implements colors of in-game actors, would you believe???
	protected float r;
	protected float b;
	protected float g;
	
	Actor() {
		point = new Point();
		point.setX(0.0f);
		point.setY(0.0f);
		r = 1.0f;
		b = 1.0f;
		g = 1.0f;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	protected void create() {
		// this method can be overridden by child class
	}
	
	protected void display() {
		// this method can be overridden by child class
	}
	
	protected void update() {
		// this method can be overridden by child class
	}
	
	protected void draw() {
		// this method can be overridden by child class
	}
}
