package is.ru.tgra;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

public class BrickBuster implements ApplicationListener {
	// constant useful for logging
	public static final String LOG = BrickBuster.class.getSimpleName();
	// a libgdx helper class that logs the current FPS each second
	private FPSLogger fpsLogger;

	// all the game variables
	private int windowWidth = 800;
	private int windowHeight = 600;	
	private boolean paused = false;								// game is paused
	//private boolean moving = false;							// ball is in motion
	private boolean gameOver = false;							// game over man!!!
	private int score = 0;										//
	private int level = 1;										// this comes later
	private int balls = 3;										//
	
	 private float elapsed= 0.01f;
	
	// Point object for coordinates of ingame thingies
	private Point point = new Point();
	ArrayList<Actor> actors = new ArrayList<Actor>();			// all objects in game are Actors, holds all the objects
	
	// objects in the game
	Launhcer launcher;
	Ball ball;
	Brick brick;
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private String textToDisplay;
	private float textWidth;
	private float textHeight;
	
	// á kannski heima í hverjum klasa fyrir sig - kemur í ljós
	// random colors for the darn bricks to be knocked down
	Random rand = new Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();
	
	// varibles that may change
	private int launcherWidth = 100;
	private int launcherHeight = 15;
	private double minAngle = Math.PI/6;						// 30 degrees in radians
	private double maxAngle = 5*Math.PI/6;						// 150 degrees in radians
	private double angle = Math.PI/3;							// ball starts at 45 degree angel
	private float speed = 10;									// ball speed
	private int radius = 10;
	
	// vantar variables til að geyma coordinates og bolta og launcher meðan player fer að skíta

	@Override
	public void create() {
		// creating instances of object thingys in the game
		launcher = new Launhcer(launcherWidth, launcherHeight, Gdx.graphics.getWidth()/2);
		//actors.add(launcher);
		ball = new Ball(radius, launcherWidth, launcherHeight, Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), (float)angle, speed);
		//actors.add(ball);
		// creating the brick wall
		for(int i = 0;i<14;i++)
		{
			for(int j =0;j<4;j++)
			{
				brick = new Brick();
				this.brick.point.setX((float)(50.0+(i*50.0)));
				this.brick.point.setY((float)(450.0+(j*25.0)));
				this.actors.add(brick);
			}
		}
		
		// set the amount of balls to use
		this.ball.setBallCount(balls);
		
		// text stuff
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		
		// logs what the h*** is happening
		Gdx.app.log(BrickBuster.LOG, "Creating game");
        fpsLogger = new FPSLogger();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(BrickBuster.LOG, "Rezising window to " + Gdx.graphics.getWidth() + " * " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		display();
		update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
		paused = true;
		
		//if game == started && !paused
		//geyma vecotr og speed somehow
		//frysta mouseX somehow
		
		Gdx.app.log(BrickBuster.LOG, "Game Paused");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
		paused = false;
		
		//if started && paused
		//speed aftur í gang á gmala vectornum
		//unfryst mouseX
		
		Gdx.app.log(BrickBuster.LOG, "Game Resume");
	}

	@Override
	public void dispose() {
		Gdx.app.log(BrickBuster.LOG, "Running the GDU (Game Disposal Unit)");
	}

	public void display() {
		// clear the screen in default color - black...  it's space after all :)
		Gdx.gl11.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        // set up the game window
        Gdx.gl11.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
        Gdx.gl11.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();
        Gdx.glu.gluOrtho2D(Gdx.gl10, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());
        
        // draw all the beautiful wonders of the universe
        drawScene();
        
        //Gdx.app.log(BrickBuster.LOG, "Running display method");
	}
	
	public void update() {
		// keyboard inputs for the game - not a priority
		if(Gdx.input.isKeyPressed(Input.Keys.Y)) {
			// starta nýju geimi somehow - spurning hvort create() sé overkill
			if(gameOver)
				this.create();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.N)) {
			// NO and you're out
			if(gameOver)
				this.dispose();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			// Don't wanna start the game just wanna QUIT???
			if(!ball.isMoving() && gameOver)
				this.dispose();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.P)) {
			// if game is running then pause the darn game and go do #2 and when returning it resumes
			// eitthvað fokk í gangi, keyrir þetta mörgum sinnum í hvert skipti
			/*
			if(paused)
				
				this.resume();
			else
				this.pause();*/
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if(!this.ball.isMoving() && !gameOver && this.ball.getBallCount() > 0) {
				this.ball.setMoving(true);
				this.ball.launchBall(angle, speed);
			}
		}
		
		// and finally all mouse button functions - all one of them (for now)
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			// if game is not over or not started, left mouse button launches the ball thingy
			if(!this.ball.isMoving() && !gameOver && this.ball.getBallCount() > 0) {
				this.ball.setMoving(true);
				this.ball.launchBall(angle, speed);	
			}
		}
		
		if(this.ball.getBallCount() == 0) {
			gameOver = true;
		}
		
		collision();
		
		//Gdx.app.log(BrickBuster.LOG, "Running update method");
	}
	
	public void drawScene() {
		// tilraun með collision
		this.launcher.display();
		this.ball.display(); 
		// each object in the array draws itself
		for(Actor a : actors) {
			Gdx.gl11.glPushMatrix();
				a.display();
			Gdx.gl11.glPopMatrix();
		}		
		
		spriteBatch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(spriteBatch, "Score: " + score + " Level: " + level + " Balls: " + ball.getBallCount(), 20, Gdx.graphics.getHeight()-20);
		spriteBatch.end();
		
		if(gameOver) {
			spriteBatch.begin();
			font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			font.draw(spriteBatch, "GAME OVER. GAME OVER MAN!", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2);
			spriteBatch.end();
		}
	}
	
	public void collision() {
		if(this.ball.point.getX() >= launcher.point.getX() && this.ball.point.getX() <= launcher.point.getX()+launcherWidth) {
			if(this.ball.point.getY()-radius <= this.launcher.point.getY()+launcherHeight) {
				this.ball.setYSpeedV(-this.ball.getYSpeedV());
			}
		}
		
		for(Actor a : actors){
			/*
			if(this.ball.point.getX() >= a.point.getX() && this.ball.point.getX() <= a.point.getX()+a.getWidth()) {
				// vantar if sem virkar bara ef boltinn er á niðurleið
				if(this.ball.point.getY()-radius == a.point.getY()+a.getHeight() || this.ball.point.getY()+radius == a.point.getY()) {
					this.ball.setYSpeedV(-this.ball.getYSpeedV());
					//a.setHit(true);
					if(a.hit) {
						actors.remove(a);
						score +=50;
					}
				}*/
			}
		}
	}
}
