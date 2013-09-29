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
		this.width = 50;
		this.height = 25;
		this.r = 1.0f;
		this.b = 0.2f;
		this.g = 0.2f;
		hit = false;
		this.create();
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
		
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(r, g, b, 1);
		renderer.filledRect(this.point.getX(), this.point.getY(), (float)width, (float)height);  // breyta variables
		renderer.flush();
		renderer.end();
		
		renderer.begin(ShapeType.Rectangle);
		renderer.setColor(0.2f, 0.2f, 0.2f, 1);
		renderer.rect(this.point.getX(), this.point.getY(), (float)width, (float)height);  // breyta variables
		renderer.flush();
		renderer.end();
	}
}
