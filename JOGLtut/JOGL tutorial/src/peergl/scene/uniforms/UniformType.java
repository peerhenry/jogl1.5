package peergl.scene.uniforms;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL4;

public enum UniformType
{
	
	FLOAT(GL4.GL_FLOAT),
	FLOAT_VEC2(GL4.GL_FLOAT_VEC2), 
	FLOAT_VEC3(GL4.GL_FLOAT_VEC3),
	FLOAT_VEC4(GL4.GL_FLOAT_VEC4),
	INT(GL4.GL_INT),
	INT_VEC2(GL4.GL_INT_VEC2),
	INT_VEC3(GL4.GL_INT_VEC3),
	INT_VEC4(GL4.GL_INT_VEC4),
	BOOL(GL4.GL_BOOL),
	BOOL_VEC2(GL4.GL_BOOL_VEC2),
	BOOL_VEC3(GL4.GL_BOOL_VEC3),
	BOOL_VEC4(GL4.GL_BOOL_VEC4),
	FLOAT_MAT2(GL4.GL_FLOAT_MAT2),
	FLOAT_MAT2x3(GL4.GL_FLOAT_MAT2x3),
	FLOAT_MAT2x4(GL4.GL_FLOAT_MAT2x4),
	FLOAT_MAT3(GL4.GL_FLOAT_MAT3),
	FLOAT_MAT3x2(GL4.GL_FLOAT_MAT3x2),
	FLOAT_MAT3x4(GL4.GL_FLOAT_MAT3x4),
	FLOAT_MAT4(GL4.GL_FLOAT_MAT4),
	FLOAT_MAT4x2(GL4.GL_FLOAT_MAT4x2),
	FLOAT_MAT4x3(GL4.GL_FLOAT_MAT4x3),
	SAMPLER_2D(GL4.GL_SAMPLER_2D),
	SAMPLER_CUBE(GL4.GL_SAMPLER_CUBE);
	
	private int typeNR;
	public int getGLTypeNr(){return typeNR;}
	
	private UniformType(final int type)
	{
		typeNR = type;
	}
	
	private static Map<Integer, UniformType> lookup = new HashMap<Integer, UniformType>();
	
	static
	{
		for(UniformType uniformType: EnumSet.allOf(UniformType.class))
			lookup.put(uniformType.getGLTypeNr(), uniformType);
	}
	
	public static UniformType get(int glTypeNr)
	{
		return lookup.get(glTypeNr);
	}
}
