package peergl.shaderprogram.shaderobjects;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;

import peergl.TextFileReader;

public class ShaderObject
{

	private GL4 gl;
	private int handle;
	private String source;
	private ShaderType shaderType;
	private String glslCode;
	private boolean shaderIsInitialized = false;
	
	public int getHandle(){ return handle; }
	public String getSourcePath(){ return source; }
	public String getGLSL(){ return glslCode; }
	public ShaderType getShaderType(){ return shaderType; }
	public boolean isInitialized(){ return shaderIsInitialized; }
	
	public ShaderObject(ShaderType type, String file)
	{
		//shaderType = getGLShaderType(type);
		shaderType = type;
		source = file;
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		makeShader(drawable);
	}
	
	private void makeShader(GLAutoDrawable drawable)
	{
		createShader(drawable);
		setShaderSource();
		gl.glCompileShader(handle);
		checkCompileStatus(handle);
		checkSahder(handle);
	}
	
	private void createShader(GLAutoDrawable drawable)
	{
		handle = gl.glCreateShader(shaderType.getGLType());
		if(handle == 0)
		{
			System.out.println("Error creating shader object!");
			drawable.destroy();
		}
	}
	
	private void setShaderSource()
	{
		glslCode = TextFileReader.readFile(source);
		//System.out.println(code);
		String[] glslArray = {glslCode};
		gl.glShaderSource(handle, 1, glslArray, null);
	}
	
	private void checkSahder(int shaderHandle)
	{
		IntBuffer logLength = IntBuffer.allocate(1);
		gl.glGetShaderiv(shaderHandle, GL4.GL_INFO_LOG_LENGTH, logLength);
		ByteBuffer log = ByteBuffer.allocate( logLength.get(0) );
		IntBuffer length = IntBuffer.allocate(1);
		gl.glGetShaderInfoLog(shaderHandle, logLength.get(0), length, log);
		
		log.rewind();
		byte[] b = new byte[log.remaining()];
		log.get(b);
		String msg = new String( b );
		System.out.println(msg);
	}
	
	private void checkCompileStatus(int handle)
	{
		IntBuffer result = IntBuffer.allocate(1);
		gl.glGetShaderiv(handle, GL4.GL_COMPILE_STATUS, result);
		if(result.get(0) == 0)
		{
			System.out.println("Shader compilation failed!");
		}
		else shaderIsInitialized = true;
	}
	
	public void dispose()
	{
		gl.glDeleteShader(handle);
	}
	
}
