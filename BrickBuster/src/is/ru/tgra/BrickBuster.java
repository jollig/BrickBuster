package is.ru.tgra;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL11;

public class BrickBuster implements ApplicationListener {
	// constant useful for logging
	public static final String LOG = BrickBuster.class.getSimpleName();
	// a libgdx helper class that logs the current FPS each second
	private FPSLogger fpsLogger;

	// all the game variables
	private int windowWidth = 800;
	private int windowHeight = 600;	
	private boolean paused = false;								// game is paused
	private boolean inMotion = false;							// ball is in motion
	private boolean gameOver = false;							// game over man!!!
	private int score = 0;										//
	private int level = 1;										// this comes later
	private int balls = 3;										//
	
	
	// Point object for coordinates of ingame thingies
	private Point point = new Point();
	ArrayList<Actor> actors = new ArrayList<Actor>();			// all objects in game are Actors, holds all the objects
	
	// objects in the game
	Launhcer launcher;
	Ball ball;
	Brick brick;
	
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
	private float speed = 100;									// ball speed
	private int radius = 10;
	
	// vantar variables til að geyma coordinates og bolta og launcher meðan player fer að skíta

	@Override
	public void create() {
		// creating instances of object thingys in the game
		launcher = new Launhcer(launcherWidth, launcherHeight, Gdx.graphics.getWidth()/2);
		actors.add(launcher);
		ball = new Ball(radius, launcherWidth, launcherHeight, Gdx.graphics.getWidth()/2);
		actors.add(ball);
		//ball - vantar að koma draslinu af stað
		for(int i = 0;i<14;i++)
		{
			for(int j =0;j<4;j++)
			{
				brick = new Brick();
				brick.point.setX((float)(50.0+(i*50.0)));
				brick.point.setY((float)(450.0+(j*25.0)));
				actors.add(brick);
			}
		}
		//brick = new Brick();
		//actors.add(brick);
		//bricks/wall
		
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
			if(!inMotion && gameOver)
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
			if(!inMotion && !gameOver){
				inMotion = true;
				// launch the ball at a given angle and velocity
				ball.lauchBall(angle, speed);
				Gdx.app.log(BrickBuster.LOG, "Spacebar pressed");
			}
			//Gdx.app.log(BrickBuster.LOG, "Spacebar pressed");
		}
		
		// and finally all mouse button functions - all one of them (for now)
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			// if game is not over or not started, left mouse button launches the ball thingy
			if(!inMotion && !gameOver){
				inMotion = true;
				// einhver vector fyrir angle sem boltinn fer af stað og hraða
				ball.lauchBall(angle, speed);
				Gdx.app.log(BrickBuster.LOG, "Left mouse button pressed");
			}
			//Gdx.app.log(BrickBuster.LOG, "Left mouse button pressed");
		}
		
		// smá tilraun með að fá boltann af stað
		if(inMotion) {
			ball.point.setX(ball.point.getX()+5);
			ball.point.setY(ball.point.getY()+5);
			if(ball.point.getX() > Gdx.graphics.getWidth()-radius)
				ball.point.setX(-ball.point.getX());
			else if(ball.point.getX() < Gdx.graphics.getWidth())
				ball.point.setX(-ball.point.getX());
			/*
			if(position_y > windowHeight-windowPosY)
				ySpeed = -ySpeed;
			else if(position_y < windowPosY)
				ySpeed = -ySpeed;
			*/
		}
				
		//Gdx.app.log(BrickBuster.LOG, "Running update method");
	}
	
	public void drawScene() {
		// each object in the array draws itself
		for(Actor a : actors) {
			Gdx.gl11.glPushMatrix();
				a.display();
			Gdx.gl11.glPopMatrix();
		}		
	}
}
