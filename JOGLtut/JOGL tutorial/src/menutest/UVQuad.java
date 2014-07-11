package menutest;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;

import peergl.math.Vec2;
import peergl.scene.Displayable;

public class UVQuad implements Displayable 
{

	GL4 gl;
	IntBuffer vaoHandle;
	IntBuffer iboHandle;
	
	Vec2 origin;
	float height, width;
	
	float uvcoords[] = new float[8];
	
	private int indices[] = {
			0,1,2,
			2,3,0
	};
	
	public UVQuad(Vec2 topleft, Vec2 topright, Vec2 bottomright, Vec2 bottomleft)
	{
		origin = new Vec2(-0.5f,0.5f);
		width = height = 1;
		uvcoords[0] = topleft.x;
		uvcoords[1] = topleft.y;
		uvcoords[2] = topright.x;
		uvcoords[3] = topright.y;
		uvcoords[4] = bottomright.x;
		uvcoords[5] = bottomright.y;
		uvcoords[6] = bottomleft.x;
		uvcoords[7] = bottomleft.y;
	}
	
	public UVQuad(UVQuad quad)
	{
		origin = quad.origin;
		width = quad.width;
		height = quad.height;
		uvcoords[0] = quad.uvcoords[0];
		uvcoords[1] = quad.uvcoords[1];
		uvcoords[2] = quad.uvcoords[2]; 
		uvcoords[3] = quad.uvcoords[3];
		uvcoords[4] = quad.uvcoords[4];
		uvcoords[5] = quad.uvcoords[5];
		uvcoords[6] = quad.uvcoords[6];
		uvcoords[7] = quad.uvcoords[7];
	}
	
	public void setWidth(float w)
	{
		width = w;
	}
	
	public void setHeight(float h)
	{
		height = h;
	}
	
	public void setDimensiont(float width, float height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setOrigin(Vec2 or)
	{
		origin = or;
	}
	
	private float[] generateVertices()
	{
		return new float[]{
				origin.x,origin.y,
				origin.x+width,origin.y,
				origin.x+width,origin.y-height,
				origin.x,origin.y-height};
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		IntBuffer vboHandles = IntBuffer.allocate(2);
		gl.glGenBuffers(2, vboHandles);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboHandles.get(0));
		Buffer vertexData = FloatBuffer.wrap(generateVertices());
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, 8*4, vertexData, GL4.GL_STATIC_DRAW);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboHandles.get(1));
		Buffer uvData = FloatBuffer.wrap(uvcoords);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, uvcoords.length*4, uvData, GL4.GL_STATIC_DRAW);
		
		iboHandle = IntBuffer.allocate(1);
		gl.glGenBuffers(1, iboHandle);
		gl.glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER, iboHandle.get(0));
		Buffer indexData = IntBuffer.wrap(indices);
		gl.glBufferData(GL4.GL_ELEMENT_ARRAY_BUFFER, indices.length*4, indexData, GL4.GL_STATIC_DRAW);
		
		vaoHandle = IntBuffer.allocate(1);
		gl.glGenVertexArrays(1, vaoHandle);
		gl.glBindVertexArray( vaoHandle.get(0) );
		gl.glEnableVertexAttribArray(0);
		gl.glEnableVertexAttribArray(1);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboHandles.get(0));
		gl.glVertexAttribPointer(0, 2, GL4.GL_FLOAT, false, 0, 0);
		
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vboHandles.get(1));
		gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 0, 0);
		
		gl.glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER, iboHandle.get(0));
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		//System.out.println("rendering quad");
		//gl.glClear( GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT );
		gl.glBindVertexArray( vaoHandle.get(0) );
		gl.glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER, iboHandle.get(0));
		gl.glDrawElements(GL4.GL_TRIANGLES, 6, GL4.GL_UNSIGNED_INT, 0);
		//gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 3);
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
	}
	
}
