package is.ru.tgra;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.BufferUtils;

public class Ball extends Actor {
	// specific ball variables
	private int radius = 10;
	private ShapeRenderer renderer = new ShapeRenderer();

	// spurning með constructors, verða sennilega að vera til staðar
	void Ball() {
		this.point.setX(0);
		this.point.setY(0);
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
	}
	
	void Ball(Point p) {
		this.point.setX(p.getX());
		this.point.setY(p.getY());
		this.r = r;
		this.b = b;
		this.g = g;
	}
	
	void Ball(float x, float y) {
		this.point.setX((int)x);
		this.point.setY((int)y);
		this.r = r;
		this.b = b;
		this.g = g;
	}
	
	@Override
	public void display(float x, float y) {
		// TODO Auto-generated method stub
		super.display(x, y);
		
		draw(x);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();

	}
	
	public void create() {
		// TODO
		
		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);	
		FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(8);
		vertexBuffer.put(new float[] {0,0, 0,radius, radius,0, radius,radius});
		vertexBuffer.rewind();
		Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, vertexBuffer);
	}

	public void draw(float x) {			// gæti þurft að bæta hér inn x og y ef það virkar ekki að sækja þetta með þeim hætti sem gert er
		// TODO draw da dam balls
		
		renderer.begin(ShapeType.FilledCircle);
		renderer.setColor(r, g, b, 1);
		renderer.filledCircle(x, 40, this.radius, 12);
		renderer.flush();
		renderer.end();
		
		//Gdx.gl11.glColor4f(r, g, b, 1.0f);
    	//Gdx.gl11.glTranslatef(this.point.getX(), this.point.getY(), 0);		// ef eitthvað klikkar er það örugglega parent Point
    	//Gdx.gl11.glTranslatef(, 10, 0);
    	//Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, 12);
    	//Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
	}
	
	// some hit detection needed
	// change in direction when hitting something
}
