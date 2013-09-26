package is.ru.tgra;

public class Vector2 {
	private float x;
	private float y;
	
	// Constructors
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
 
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void normalize() {
        // sets length to 1
        double length = Math.sqrt(x*x + y*y); 
     
        if (length != 0.0) {
        	x = x/(float)length;
        	y = y/(float)length;
        	/*
            float s = 1.0f / (float)length;
            x = x*s;
            y = y*s;
            */
        }
    }
    
    // overridden operators, which is not known yet
    // +
    // -
    // *
    // ==
}
