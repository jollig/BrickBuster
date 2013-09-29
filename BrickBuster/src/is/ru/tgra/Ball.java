package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ball extends Actor {
	// specific ball variables
	private float ballAngle;
	private float ballSpeed;
	private int radius, width, height;
	private boolean inMotion = false;
	
	private ShapeRenderer renderer;
	
	private Vector2 speedV;

	// constructors, some will cease to exist
	Ball() {
		super();
		this.point.setX(0.0f);
		this.point.setY(0.0f);
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
		this.create();
	}
	
	Ball(int r, int width, int height, int xPos) {
		super();
		this.radius = r;
		this.width = width;
		this.height = height;
		this.point.setX(xPos);
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
		this.create();
	}
	
	@Override
	public void create() {
		renderer = new ShapeRenderer();
		speedV = new Vector2();
	}
	
	@Override
	public void display() {
		update();
		draw();
	}

	@Override
	public void update() {
		// vantar tékk hvort bolti sé kominn niður fyrir launcher því þá er eitt líf farið
		updateMouse();
		angleSpeed();
	}
	
	@Override
	public void draw() {
		renderer.begin(ShapeType.FilledCircle);
		renderer.setColor(r, g, b, 1);
		renderer.filledCircle(this.point.getX(), this.point.getY(), this.radius, 12);
		renderer.flush();
		renderer.end();
	}
	
	public void updateMouse() {
		// if ball is not bounching around the universe it should sit on top of the launcher and move with it until set in motion
		if(!inMotion) {							// vantar gameover líka
			this.point.setY(height*2);
			if(Gdx.input.getX()-width/2 <= 0) {
				this.point.setX(width/2);
	        }
			else if(Gdx.input.getX()+width/2 >= Gdx.graphics.getWidth()) {
				this.point.setX(Gdx.graphics.getWidth()-width/2);
			} 
			else {
				this.point.setX(Gdx.input.getX());
			}
		}	
	}
	
	public void lauchBall(double angle, float speed) {
		this.ballAngle = (float)angle;
		this.ballSpeed = speed;
	}
	
	// how fast the ball is moving relative to the x- and y-axis
	public void angleSpeed() {	
		while(ballAngle>2*Math.PI) ballAngle-=2*Math.PI;
		while(ballAngle<0.0) ballAngle+=2*Math.PI;
		
		speedV.setX((float)((double)ballSpeed*Math.cos((double)ballAngle)));
		speedV.setY((float)((double)ballSpeed*Math.sin((double)ballAngle)));
	}
	
	// some hit detection needed
	// change in direction when hitting something
}
