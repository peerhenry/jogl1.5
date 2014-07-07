package peergl.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.media.opengl.GLAutoDrawable;

public class Scene implements Displayable
{

	protected List<Displayable> displayables = new ArrayList<Displayable>();
	
	public Scene()
	{
		
	}
	
	protected void applyToAll(Consumer<Displayable> function)
	{
		for(Displayable ro: displayables) function.accept(ro);
	}
	
	public void addRenderObject(Displayable object)
	{
		displayables.add(object);
	}
	
	public void clear(GLAutoDrawable drawable)
	{
		applyToAll(r -> r.dispose(drawable));
		displayables.clear();
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		applyToAll(r->r.init(drawable));
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		applyToAll(r->r.dispose(drawable));
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		applyToAll(r->r.display(drawable));
	}
	
}
