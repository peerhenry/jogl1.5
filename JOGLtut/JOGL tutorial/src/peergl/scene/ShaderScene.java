package peergl.scene;

import javax.media.opengl.GLAutoDrawable;

import peergl.shaderprogram.ShaderProgram;

public class ShaderScene extends Scene
{

	private ShaderProgram program;
	private UniformFactory uniforms;
	
	public UniformFactory getUniformFactory(){return uniforms;}
	
	public ShaderScene(ShaderProgram program)
	{
		this.program = program;
		uniforms = new UniformFactory(program);
	}
	
	public void addRenderObject(Displayable ro)
	{
		displayables.add(ro);
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		program.init(drawable);
		uniforms.init(drawable);
		super.init(drawable);
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		program.use();
		super.display(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		super.dispose(drawable);
		if( !program.equals(null) ) program.dispose();
	}
	
}
