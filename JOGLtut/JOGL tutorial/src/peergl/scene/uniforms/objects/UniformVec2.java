package peergl.scene.uniforms.objects;

import javax.media.opengl.GL4;

import peergl.math.Vec2;
import peergl.scene.uniforms.Uniform;

public class UniformVec2 extends UniformObject
{

	public Vec2 vec;
	
	public UniformVec2(Uniform u)
	{
		super(u);
		vec = new Vec2();
	}

	@Override
	public void set(GL4 gl)
	{
		gl.glUniform2f(getLocation(), vec.x, vec.y);
	}
	
}
