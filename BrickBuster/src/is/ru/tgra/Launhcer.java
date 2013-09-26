package is.ru.tgra;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class Launhcer extends Actor{
	// launcher dimensions and position
	private int width = 0;
	private int height = 0;
	//private int mouseX = 0;
	
	// Launcher constructors
	public Launhcer() {		
		// default size, position and color of the launcher
		width = 100;
		height = 20;
		this.point.setX(0);
		this.r = 1.0f;
		this.b = 1.0f;
		this.g = 1.0f;
		this.create();
	}
	
	public Launhcer(int w, int h) {
		this.width = w;
		this.height = h;
		this.create();
	}

	@Override
	public void display(float x, float y) {
		// movement of the launcher only requires the x coordinate
		draw(x, height);
	}

	@Override
	public void update() {
	}
	
	public void create() {
		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);	
		FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(8);
		vertexBuffer.put(new float[] {0,0, 0,height, width,0, width,height});
		vertexBuffer.rewind();
		Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, vertexBuffer);
	}
	
	public void draw(float x, int height){
		Gdx.gl11.glColor4f(r, g, b, 1.0f);
    	Gdx.gl11.glTranslatef(x, height/2, 0);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
	}
}
