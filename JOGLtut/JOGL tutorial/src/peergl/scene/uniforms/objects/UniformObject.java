package peergl.scene.uniforms.objects;

import javax.media.opengl.GL4;

import peergl.scene.uniforms.Uniform;

public abstract class UniformObject
{

	protected Uniform uniform;
	
	public UniformObject(final Uniform u)
	{
		uniform = u;
	}
	
	public int getLocation()
	{
		return uniform.getLocation();
	}
	
	/**
	 * Set this uniform value in OpenGL.
	 * @param gl
	 */
	public abstract void set(GL4 gl);
	
}
