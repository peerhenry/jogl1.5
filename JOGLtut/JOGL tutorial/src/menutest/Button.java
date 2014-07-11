package menutest;

import javax.media.opengl.GLAutoDrawable;

public class Button extends UVQuad
{

	public Button(UVQuad q)
	{
		super(q);
	}
	
	@Override
	public void init(GLAutoDrawable drawable)
	{
		super.init(drawable);
		//System.out.println("initting button.");
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		super.display(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		super.dispose(drawable);
	}
	
}
