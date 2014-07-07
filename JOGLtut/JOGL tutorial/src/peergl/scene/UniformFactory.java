package peergl.scene;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;

import peergl.scene.uniforms.*;
import peergl.scene.uniforms.objects.*;
import peergl.shaderprogram.ShaderProgram;

public class UniformFactory
{

	private GL4 gl;
	private Map<Integer, Uniform> uniforms = new HashMap<Integer, Uniform>();
	private ShaderProgram program;
	
	public UniformFactory(ShaderProgram program)
	{
		this.program = program;
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL4();
		int programHandle = program.getHandle();
		IntBuffer numActiveUniforms = IntBuffer.allocate(1);
		IntBuffer uniformNameLength = IntBuffer.allocate(1);
		gl.glGetProgramiv(programHandle, GL4.GL_ACTIVE_ATTRIBUTES, numActiveUniforms);
		gl.glGetProgramiv(programHandle, GL4.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH, uniformNameLength);
		
		IntBuffer length = IntBuffer.allocate(1);
		IntBuffer size = IntBuffer.allocate(1);
		IntBuffer type = IntBuffer.allocate(1);
		ByteBuffer nameBytes = ByteBuffer.allocate(uniformNameLength.get(0));
		
		for(int i = 0; i< numActiveUniforms.get(0); i++)
		{
			gl.glGetActiveUniform(programHandle, i, uniformNameLength.get(0), length, size, type, nameBytes);
			String name = new String(nameBytes.array()).substring(0, length.get(0));
			int location = gl.glGetUniformLocation(programHandle, name);
			uniforms.put( location, new Uniform(location, type.get(0), name ) );
			System.out.println(""+location);
		}
	}
	
	public UniformObject generateUniformObject(String name) throws NoUniformException
	{
		int location = gl.glGetUniformLocation(program.getHandle(), name);
		return generateUniformObject(location);
	}
	
	public UniformObject generateUniformObject(int location) throws NoUniformException
	{
		if(uniforms.containsKey(location)) return getUniformObject(uniforms.get(location));
		else throw new NoUniformException();
	}
	
	private UniformObject getUniformObject(Uniform u)
	{
		UniformType t = u.getType();
		
		switch(t)
		{
		case FLOAT:
			return new UniformFloat(u);
		case FLOAT_VEC2:
			return new UniformVec2(u);
		case FLOAT_VEC3:
			return new UniformVec3(u);
		}
		
		return null;
	}
	
}
