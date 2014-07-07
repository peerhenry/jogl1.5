package peergl.textures;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL4;

public class Texture
{

	private GL4 gl;
	private int handle;
	
	private byte[] data;
	private int width, height;
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}

	public byte[] getData(){return data;}
	public Buffer getBuffer(){return ByteBuffer.wrap(data);}
	
	protected Texture(){}
	
	protected Texture(GL4 gl, int w, int h)
	{
		width = w;
		height = h;
		this.gl = gl;
		generate();
	}
	
	private void generate()
	{
		IntBuffer tex = IntBuffer.allocate(1);
		gl.glGenTextures(1, tex);
		handle = tex.get(0);
	}
	
	protected void setData(byte[] data)
	{
		this.data = data;
	}
	
	public void setInUniform(int programHandle, String uniformName)
	{
		gl.glBindTexture(GL4.GL_TEXTURE_2D, handle);
		
		gl.glTexImage2D(GL4.GL_TEXTURE_2D, 0, GL4.GL_RGBA, width, height, 0, GL4.GL_RGBA, GL4.GL_UNSIGNED_BYTE, getBuffer());
		
		//gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_S, GL4.GL_CLAMP_TO_BORDER);
		//gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_T, GL4.GL_CLAMP_TO_BORDER);
		
		setMagFilterLinear();
		setMinFilterLinear();
		
		//gl.glActiveTexture(GL4.GL_TEXTURE0);
		int texLoc = gl.glGetUniformLocation(programHandle, uniformName);
		gl.glUniform1i(texLoc, 0);
	}
	
	public void setMagFilterNearest()
	{
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_NEAREST);
	}
	
	public void setMinFilterNearest()
	{
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_NEAREST);
	}
	
	public void setMagFilterLinear()
	{
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_LINEAR);
	}
	
	public void setMinFilterLinear()
	{
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_LINEAR);
	}
	
}
