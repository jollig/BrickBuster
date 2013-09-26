package is.ru.tgra;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.Point;

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
	private double minAngle = Math.PI/6;						// 30 degrees in radians
	private double maxAngle = 5*Math.PI/6;						// 150 degrees in radians
	private double angle = Math.PI/3;							// ball starts at 45 degree angel
	private float mouseX = 0;									// for mouse control only the x-coordinate is needed
	private float posX, posY = 0;								// for positioning other objects x & y are both needed
	private boolean paused = false;
	private boolean started = false;
	private boolean gameOver = false;
	private int score = 0;
	private int level = 1;
	private int balls = 3;
	
	
	// Point object for coordinates of ingame thingies
	private Point point = new Point();
	ArrayList<Actor> actors = new ArrayList<Actor>();			// all objects in game are Actors, holds all the objects
	
	// � kannski heima � hverjum klasa fyrir sig - kemur � lj�s
	// random colors for the darn bricks to be knocked down
	Random rand = new Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();
	
	// varibles that may change
	private int launcherWidth = 100;
	private int launcherHeight = 20;
	
	// vantar variables til a� geyma coordinates og bolta og launcher me�an player fer a� sk�ta

	@Override
	public void create() {
		mouseX = Gdx.graphics.getWidth()/2;						// we want our ball launching thingy to appear in the middle of the screen at start of game
		
		// creating instances of object thingys in the game
		Launhcer launcher = new Launhcer();
		actors.add(launcher);
		Ball ball = new Ball();
		actors.add(ball);
		//ball
		//bricks/wall
		//whatever else comes later
		
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
		//speed aftur � gang � gmala vectornum
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
		// avoid that the launcher skips out of town - off the edge of the window and into the abyss
		// hold on to trying point here as it might conflict with the mouse coordinate pickup
		if(Gdx.input.getX()-50 <= 0) {
			//this.point.setX(0);
        	mouseX = 0;
        }
		else if(Gdx.input.getX()+50 >= Gdx.graphics.getWidth()) {
			//this.point.setX(Gdx.graphics.getWidth()-launcherWidth);
			mouseX = Gdx.graphics.getWidth()-launcherWidth;
		}
		else {
			//this.point.setX(Gdx.input.getX()-launcherWidth/2);
			mouseX = Gdx.input.getX()-launcherWidth/2;
		}
		
		// keyboard inputs for the game
		if(Gdx.input.isKeyPressed(Input.Keys.Y)) {
			// starta n�ju geimi somehow - spurning hvort create() s� overkill
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
			if(!started)
				this.dispose();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.P)) {
			// if game is running then pause the darn game and go do #2 and when returning it resumes
			// eitthva� fokk � gangi, keyrir �etta m�rgum sinnum � hvert skipti
			/*
			if(paused)
				
				this.resume();
			else
				this.pause();*/
		}
		
		// and finally all mouse button functions - all one of them (for now)
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			// if game is not over or not started, left mouse button launches the ball thingy
			if(!gameOver && !started){
				started = true;
				// einhver vector fyrir angle sem boltinn fer af sta� og hra�a
			}
			// kannski keyra eitthva� fall sem launchar boltadraslinu - e�a sleppa �v� og bara gera �etta � if setningunni
		}
				
		//Gdx.app.log(BrickBuster.LOG, "Running update method");
	}
	
	public void drawScene() {
		// TODO
				
		// for some reason only draws the ball...
		for(Actor a : actors) {					// each object in the array draws itself
			Gdx.gl11.glPushMatrix();
				//a.display(point);
				a.display(mouseX, posY);
			Gdx.gl11.glPopMatrix();
		}		
	}
}
