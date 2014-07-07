package peergl.scene;

import javax.media.opengl.GLAutoDrawable;

public interface Displayable 
{
	
	public void init(GLAutoDrawable drawable);
	
	public void display(GLAutoDrawable drawable);
	
	public void dispose(GLAutoDrawable drawable);
	
}
