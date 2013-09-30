package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Actor {
	// specific ball variables
	private float ballAngle;
	private float ballSpeed;
	private int radius, width, height, windowW, windowH, ballCount;
	private boolean moving = false;
	
	private ShapeRenderer renderer;
	
	private Vector2 speedV;

	// constructors, some will cease to exist
	Ball() {
		super();
		this.radius = 10;
		this.point.setX(0.0f);
		this.point.setY(0.0f);
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
		this.create();
	}
	
	Ball(int r, int width, int height, int xPos,int windowW, int windowH, float angle, float speed) {
		super();
		this.radius = r;
		this.width = width;
		this.height = height;
		this.point.setX(xPos);
		this.windowW = windowW;
		this.windowH = windowH;
		this.ballAngle = angle;
		this.ballSpeed = speed;
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
		this.create();
	}
	
	@Override
	public void create() {
		renderer = new ShapeRenderer();
		speedV = new Vector2();
		ballCount = 3;
	}
	
	@Override
	public void display() {
		draw();
		update();
	}

	@Override
	public void update() {
		// if the ball is not moving it follows the launcher until it is set into motion
		if(!moving)
			updateMouse();
		
		// collision checks with left, right and top margins of the window
		if(moving) {	
			// fara vel í gegnum þetta og sjá hvað í f..... er í gangi
			if(this.point.getX() > Gdx.graphics.getWidth()-radius) {
				speedV.x = -speedV.x;
				this.point.setX(this.point.getX()+speedV.x);
			}
			else if(this.point.getX() < radius) {
				speedV.x = -speedV.x;
				this.point.setX(this.point.getX()+speedV.x);
			}
			if(this.point.getY() > Gdx.graphics.getHeight()-radius) {
				speedV.y = -speedV.y;
				this.point.setY(this.point.getY()+speedV.y);
			}
			else if(this.point.getY() < 0-radius)
				resetBall();
			else {
				this.point.setX(this.point.getX()+speedV.x);
				this.point.setY(this.point.getY()+speedV.y);
			}
		}
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
	
	public void launchBall(double angle, float speed) {
		angleSpeed();
	}
	
	// how fast the ball is moving relative to the x- and y-axis
	public void angleSpeed() {	
		while(ballAngle>2*Math.PI)
			ballAngle-=2*Math.PI;
		while(ballAngle<0.0)
			ballAngle+=2*Math.PI;
		
		speedV.x = (float)((double)ballSpeed*Math.cos((double)ballAngle));
		speedV.y = (float)((double)ballSpeed*Math.sin((double)ballAngle));
	}
	
	// some hit detection needed
	// change in direction when hitting something
	
	public void resetBall() {
		moving = false;
		ballCount--;
	}

	protected boolean isMoving() {
		return moving;
	}

	protected void setMoving(boolean moving) {
		this.moving = moving;
	}

	
	protected int getBallCount() {
		return ballCount;
	}

	
	protected void setBallCount(int ballCount) {
		this.ballCount = ballCount;
	}
	
	public void bounch() {
		// don't have a clue where to start
		
	}
}
