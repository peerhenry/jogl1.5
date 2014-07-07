package peergl;

import java.nio.IntBuffer;

import javax.media.opengl.GL4;

public class GLObjectGenerator
{

	public static int genVAO(GL4 gl)
	{
		IntBuffer buffer = IntBuffer.allocate(1);
		gl.glGenVertexArrays(1, buffer);
		return buffer.get(0);
	}
	
	public static int genBufferObject(GL4 gl)
	{
		IntBuffer buffer = IntBuffer.allocate(1);
		gl.glGenBuffers(1, buffer);
		return buffer.get(0);
	}
	
}
