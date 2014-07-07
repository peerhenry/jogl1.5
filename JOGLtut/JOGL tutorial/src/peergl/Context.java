package peergl;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.Animator;

public class Context
{

	private int width;
	private int height;
	private Frame frame;
	private Animator animator;
	private GLEventListener listener; 
	
	public Context(int w, int h, GLEventListener el)
	{
		width = w;
		height = h;
		listener = el;
		setupContext();
	}
	
	private void setupContext()
	{
		GLProfile profile = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(profile);
		final GLCanvas canvas = new GLCanvas(caps);
		if(listener != null) canvas.addGLEventListener(listener);
		
		frame = new Frame("JOGL Frame");
		frame.add(canvas);
		canvas.setSize(width, height);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		animator = new Animator(canvas);
		
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				//System.out.println("Closing le window");
				new Thread(new Runnable(){
					public void run()
					{
						animator.stop();
						//canvas.disposeGLEventListener(listener, true);
						canvas.destroy();
						System.exit(0);
					}
				}).start();
			}
		});
	}
	
	public void start()
	{
		frame.setVisible(true);
		animator.start();
	}
	
}
