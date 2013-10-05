package is.ru.tgra;

public abstract class Actor {
	// a Point object to store coordinates of the Actor
	protected Point point;
	
	// is object hit, some objects are not affected by this
	protected boolean hit;
	
	// this application is wacky and implements colors of in-game actors, would you believe???
	protected float r;
	protected float b;
	protected float g;
	
	Actor() {
		point = new Point();
		hit = false;
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

	protected float getWidth() {
		// this method can be overridden by child class
		return 0;
	}
	
	protected float getHeight() {
		// this method can be overridden by child class
		return 0;
	}

	protected boolean isHit() {
		return hit;
	}

	protected void setHit(boolean hit) {
		this.hit = hit;
	}
}
