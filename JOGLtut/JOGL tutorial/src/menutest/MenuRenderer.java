package menutest;

import java.util.List;
import java.util.ArrayList;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import peergl.math.Vec2;
//import peergl.GLSceneRenderer;
import peergl.shaderprogram.ShaderProgram;
import peergl.shaderprogram.shaderobjects.ShaderObject;
import peergl.shaderprogram.shaderobjects.ShaderType;
import peergl.textures.Texture;
import peergl.textures.TextureFactory;

public class MenuRenderer implements GLEventListener
{

	private ShaderProgram program;
	private List<Button> buttons = new ArrayList<Button>();
	GL4 gl;
	
	public MenuRenderer()
	{
		program = new ShaderProgram();
		program.attachShader(new ShaderObject(ShaderType.VERTEX, "//menushaders//vertex.glsl"));
		program.attachShader(new ShaderObject(ShaderType.FRAGMENT, "//menushaders//fragment.glsl"));
		addButtons();
	}
	
	private void addButtons()
	{
		for(int i = 0; i<4; i++) addButton(i);
	}
	
	private void addButton(int n)
	{
		UVQuad quad = new UVQuad(
				new Vec2(0,0.25f*n),
				new Vec2(1,0.25f*n),
				new Vec2(1,0.25f*(n+1)),
				new Vec2(0,0.25f*(n+1))
				);
		
		Button newButton = new Button(quad);
		newButton.setOrigin( new Vec2(-0.5f,0.5f - n*0.25f) );
		newButton.setHeight(0.25f);
		buttons.add(newButton);
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		program.init(drawable);
		loadTexture();
		//super.init(drawable);
		for(Button button: buttons)
		{
			button.init(drawable);
		}
		gl.glClearColor(0.1f, 0.3f, 0.9f, 1);
		gl.glBlendFunc(GL4.GL_SRC_ALPHA, GL4.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL4.GL_BLEND);
	}
	
	private void loadTexture()
	{
		try{
			
			Texture tex = TextureFactory.generateTextureFromPNG(gl, "//resources//menuatlas.png");
			
			tex.setInUniform(program.getHandle(), "Tex");
		} catch(Exception e){System.out.println("there be problems.");}
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		gl.glClear( GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT );
		for(Button button: buttons)
		{
			button.display(drawable);
		}
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		
	}
	
}
