package peergl.scene.uniforms.objects;

import javax.media.opengl.GL4;

import peergl.math.Vec3;
import peergl.scene.uniforms.Uniform;

public class UniformVec3 extends UniformObject
{

	public Vec3 vec;
	
	public UniformVec3(Uniform uniform)
	{
		super(uniform);
		vec = new Vec3();
	}
	
	@Override
	public void set(GL4 gl)
	{
		gl.glUniform3f(uniform.getLocation(), vec.x, vec.y, vec.z);
	}
	
}
