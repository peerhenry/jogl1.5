package peergl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import peergl.scene.Scene;

public class GLSceneRenderer implements GLEventListener
{

	private List<Scene> scenes = new ArrayList<Scene>();
	
	public GLSceneRenderer()
	{
		
	}
	
	public void addScene(Scene s)
	{
		scenes.add(s);
	}
	
	public void applyToAll(Consumer<Scene> function)
	{
		for(Scene s: scenes)
		{
			function.accept(s);
		}
	}
	
	public void clear(GLAutoDrawable drawable)
	{
		applyToAll(s -> s.dispose(drawable));
		scenes.clear();
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		applyToAll(s->s.init(drawable));
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		applyToAll(s->s.dispose(drawable));
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		applyToAll(s->s.display(drawable));
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		//System.out.println("width and hight: " + width + ", " + height);
	}
	
}
