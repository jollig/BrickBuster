package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Launhcer extends Actor{
	// launcher dimensions and position
	private int width;
	private int height;
	
	private ShapeRenderer renderer;
	
	// Launcher constructors
	public Launhcer() {
		super();
		// default size, position and color of the launcher
		width = 100;
		height = 20;
		this.point.setX(width/2);
		this.point.setY(height);
		r = 0.5f;
		b = 0.5f;
		g = 0.5f;
		this.create();
	}
	
	// constructor taking as variables width and height of launcher, x- and y-coordinates of initial position of the launcher
	public Launhcer(int width, int height, int xPos) {
		super();
		this.width = width;
		this.height = height;
		this.point.setX(xPos);
		r = 0.5f;
		b = 0.5f;
		g = 0.5f;
		this.create();
	}
	
	@Override
	public void create() {
		renderer = new ShapeRenderer();
	}

	@Override
	public void display() {
		// movement of the launcher only requires the x coordinate
		update();
		draw();
	}
	
	@Override
	public void update() {
		updateMouse();
	}
	
	@Override
	public void draw(){
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(r, g, b, 1);
		renderer.filledRect(this.point.getX(), this.point.getY()+height/2, (float)width, (float)height);
		renderer.flush();
		renderer.end();
	}
	
	public void updateMouse() {
		// avoid that the launcher skips out of town - off the edge of the window and into the abyss
		// launcher does not leave the window to the left
		if(Gdx.input.getX()-width/2 <= 0) {
			this.point.setX(0);
        }
		// launcher does not leave the window to the right
		else if(Gdx.input.getX()+width/2 >= Gdx.graphics.getWidth()) {
			this.point.setX(Gdx.graphics.getWidth()-width);
		}
		else {
			this.point.setX(Gdx.input.getX()-width/2);
		}
	}
}
