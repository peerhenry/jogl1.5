package peergl;

import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class GLInfoProvider
{

	private GLAutoDrawable g;
	private GL gl;
	
	public GLInfoProvider(GLAutoDrawable drawable)
	{
		g = drawable;
		gl = g.getGL();
	}
	
	public String getGLRenderer()
	{
		return gl.glGetString(GL.GL_RENDERER);
	}
	
	public String getGLVendor()
	{
		return gl.glGetString(GL.GL_VENDOR);
	}
	
	public String getGLVersion()
	{
		return gl.glGetString(GL.GL_VERSION);
	}
	
	public String getViewportSize()
	{
		IntBuffer buffer = IntBuffer.allocate(4);
		gl.glGetIntegerv(GL.GL_VIEWPORT, buffer);
		return "" + buffer.get(2) + "×" + buffer.get(3) ;
	}
	
	public String getViewportPosition()
	{
		IntBuffer buffer = IntBuffer.allocate(4);
		gl.glGetIntegerv(GL.GL_VIEWPORT, buffer);
		return "" + buffer.get(0) + ", " + buffer.get(1) ;
	}
	
}
