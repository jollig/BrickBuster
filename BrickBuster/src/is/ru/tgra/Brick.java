package is.ru.tgra;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Brick extends Actor{
	// brick dimensions and position
	private float width;
	private float height;
	
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
		// if a brick has been hit it is not drawn again
		if(hit) {
			return;
		}
		
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(r, g, b, 1);
		renderer.filledRect(this.point.getX(), this.point.getY(), (float)width, (float)height);
		renderer.flush();
		renderer.end();
		
		// a small frame to distinguish one brick from another
		renderer.begin(ShapeType.Rectangle);
		renderer.setColor(0.2f, 0.2f, 0.2f, 1);
		renderer.rect(this.point.getX(), this.point.getY(), (float)width, (float)height);
		renderer.flush();
		renderer.end();
	}

	public float getWidth() {
		return (float)width;
	}

	public float getHeight() {
		return (float)height;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
