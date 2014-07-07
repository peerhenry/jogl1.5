package peergl.shaderprogram;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;

import peergl.shaderprogram.shaderobjects.ShaderObject;

public class ShaderProgram
{
	public static void say(String msg){System.out.println(msg);}
	
	private GL4 gl;
	private int programHandle;
	private List<ShaderObject> shaderObjects = new ArrayList<ShaderObject>();
	
	public int getHandle(){return programHandle;}
	
	public ShaderProgram()
	{
		
	}
	
	public void attachShader(ShaderObject shader)
	{
		shaderObjects.add(shader);
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		initShaders(drawable);
		makeProgram(drawable);
	}
	
	private void initShaders(GLAutoDrawable drawable)
	{
		for(ShaderObject so: shaderObjects)
		{
			if(!so.isInitialized()) so.init(drawable);
		}
	}
	
	private void makeProgram(GLAutoDrawable drawable)
	{
		createProgram(drawable);
		for(ShaderObject so: shaderObjects)
		{
			gl.glAttachShader(programHandle, so.getHandle());
		}
		link();
		use();
		checkProgram();
	}
	
	public void link()
	{
		gl.glLinkProgram(programHandle);
	}
	
	public void use()
	{
		gl.glUseProgram(programHandle);
	}
	
	private void createProgram(GLAutoDrawable drawable)
	{
		programHandle = gl.glCreateProgram();
		if(programHandle == 0)
		{
			System.out.println("Error creating shader program!");
			drawable.destroy();
		}
	}
	
	private void checkProgram()
	{
		IntBuffer result = IntBuffer.allocate(1);
		gl.glGetProgramiv(programHandle, GL4.GL_LINK_STATUS, result);
		if(result.get(0) == 0)
		{
			System.out.println("Program linking failed!");
		}
		
		IntBuffer logLength = IntBuffer.allocate(1);
		gl.glGetProgramiv(programHandle, GL4.GL_INFO_LOG_LENGTH, logLength);
		ByteBuffer log = ByteBuffer.allocate(logLength.get(0));
		IntBuffer length = IntBuffer.allocate(1);
		gl.glGetProgramInfoLog(programHandle, logLength.get(0), length, log);
		
		/*char c0 = (char)log.get(0);
		char c1 = (char)log.get(1);
		char c2 = (char)log.get(2);
		String t = "" + c0 + c1 + c2;
		System.out.println(t);//*/
		
		//System.out.println("log length: " + logLength.get(0));
		//System.out.println("actual log length: " + length.get(0));
		
		log.rewind();
		byte[] b = new byte[log.remaining()];
		log.get(b);
		String msg = new String( b );
		System.out.println(msg);
	}
	
	public void dispose()
	{
		for(ShaderObject so: shaderObjects)
		{
			gl.glDetachShader( programHandle, so.getHandle() );
			so.dispose();
		}
		gl.glDeleteProgram(programHandle);
	}
}
