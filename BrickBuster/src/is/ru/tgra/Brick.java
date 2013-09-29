package is.ru.tgra;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Brick extends Actor{
	// brick dimensions and position
	private int width;
	private int height;
	
	private boolean hit;
	
	private ShapeRenderer renderer;

	public Brick() {
		super();
		// TODO
		hit = false;
	}
	
	@Override
	public void create() {
		renderer = new ShapeRenderer();
	}

	@Override
	public void display() {
		update();
		draw();
	}

	@Override
	public void update() {
		// TODO
	}
	
	@Override
	public void draw() {
		if(hit) {
			return;
		}
		/*
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(r, g, b, 1);
		renderer.filledRect(this.point.getX(), this.point.getY()+height/2, (float)width, (float)height);  // breyta variables
		renderer.flush();
		renderer.end();
		*/		
	}
}
