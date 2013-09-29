package is.ru.tgra;

public class Point {
	private float x;
	private float y;
	
	// Constructors
    Point() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
 
    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
