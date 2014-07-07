package peergl.scene;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL4;

import peergl.scene.uniforms.objects.UniformObject;

public abstract class RenderObject implements Displayable
{

	private List<UniformObject> uniforms = new ArrayList<UniformObject>();
	
	public void attachUniformObject(UniformObject uniform)
	{
		uniforms.add(uniform);
	}
	
	public void setUniforms(GL4 gl)
	{
		for(UniformObject u: uniforms) u.set(gl);
	}
	
}
