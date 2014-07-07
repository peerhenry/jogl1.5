package menutest;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
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
	private UVQuad quad;
	GL4 gl;
	
	public MenuRenderer()
	{
		program = new ShaderProgram();
		program.attachShader(new ShaderObject(ShaderType.VERTEX, "//menushaders//vertex.glsl"));
		program.attachShader(new ShaderObject(ShaderType.FRAGMENT, "//menushaders//fragment.glsl"));
		quad = new UVQuad(
				new Vec2(0,0),
				new Vec2(1,0),
				new Vec2(1,1),
				new Vec2(0,1)
				);
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		program.init(drawable);
		loadTexture();
		//super.init(drawable);
		quad.init(drawable);
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
		quad.display(drawable);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		
	}
	
}
