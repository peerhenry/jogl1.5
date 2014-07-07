package jogltut;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;

import peergl.scene.Displayable;
import peergl.scene.RenderObject;

public class Triangle extends RenderObject
{

	private GL4 gl;
	private IntBuffer bufferHandles;	// wrong in the manual
	private IntBuffer vaoHandle;
	private float[] vertices = {
			   0 , 0.5f,
			-0.5f,-0.5f,
			 0.5f,-0.5f
			};
	private float[] colors = {
			1,0,0,
			0,1,0,
			0,0,1};
	
	
	public void init(GLAutoDrawable drawable)
	{	
		gl = drawable.getGL().getGL4();
		generateBuffers();
		generateVAO();
		gl.glClearColor(0, 0, 0.1f, 1);
	}
	
	private void generateBuffers()
	{
		bufferHandles = IntBuffer.allocate(2);
		gl.glGenBuffers(2, bufferHandles);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, bufferHandles.get(0));
		Buffer vertexData = FloatBuffer.wrap(vertices);
		int vertexBytes = vertices.length*4;
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, vertexBytes, vertexData, GL4.GL_STATIC_DRAW);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, bufferHandles.get(1));
		Buffer colorData = FloatBuffer.wrap(colors);
		int colorBytes = colors.length*4;
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, colorBytes, colorData, GL4.GL_STATIC_DRAW);
	}
	
	private void generateVAO()
	{
		vaoHandle = IntBuffer.allocate(1);
		gl.glGenVertexArrays(1, vaoHandle);
		//System.out.println( "VAO handle: " + vaoHandle.get(0) );
		gl.glBindVertexArray( vaoHandle.get(0) );
		gl.glEnableVertexAttribArray(0);
		gl.glEnableVertexAttribArray(1);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, bufferHandles.get(0));
		gl.glVertexAttribPointer(0, 2, GL4.GL_FLOAT, false, 0, 0);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, bufferHandles.get(1));
		gl.glVertexAttribPointer(1, 3, GL4.GL_FLOAT, false, 0, 0);
	}
	
	public void display(GLAutoDrawable drawable)
	{
		gl.glClear( GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT );
		
		//setUniforms(gl);
		
		gl.glBindVertexArray( vaoHandle.get(0) );
		
		gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 3);
	}
	
	public void dispose(GLAutoDrawable drawable)
	{
		gl.glDeleteBuffers(2, bufferHandles);
		gl.glDeleteVertexArrays(1, vaoHandle);
	}
	
}
