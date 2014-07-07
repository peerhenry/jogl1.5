package menutest;

import javax.media.opengl.GLAutoDrawable;

import peergl.scene.Displayable;

public class Button implements Displayable
{

	private UVQuad quad;

	public Button(UVQuad q)
	{
		quad = q;
	}
	
	@Override
	public void init(GLAutoDrawable drawable)
	{
		quad.init(drawable);
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		quad.display(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		quad.dispose(drawable);
	}
	
}
