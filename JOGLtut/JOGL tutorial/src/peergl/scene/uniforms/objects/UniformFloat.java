package peergl.scene.uniforms.objects;

import javax.media.opengl.GL4;

import peergl.scene.uniforms.Uniform;

public class UniformFloat extends UniformObject
{

	public float value;
	
	public UniformFloat(Uniform u)
	{
		super(u);
	}
	
	public void set(GL4 gl)
	{
		gl.glUniform1f(uniform.getLocation(), value);
	}
	
}
