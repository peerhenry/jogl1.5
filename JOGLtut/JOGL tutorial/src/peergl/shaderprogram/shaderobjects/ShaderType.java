package peergl.shaderprogram.shaderobjects;

import javax.media.opengl.GL4;

public enum ShaderType {
	VERTEX, FRAGMENT, TESS_CONTROL, TESS_EVAL;
	
	public int getGLType()
	{
		return getGLShaderType(this);
	}
	
	public static int getGLShaderType(ShaderType t)
	{
		switch(t)
		{
		case VERTEX:
			return GL4.GL_VERTEX_SHADER;
		case FRAGMENT:
			return GL4.GL_FRAGMENT_SHADER;
		case TESS_CONTROL:
			return GL4.GL_TESS_CONTROL_SHADER;
		case TESS_EVAL:
			return GL4.GL_TESS_EVALUATION_SHADER;
		}
		return 0;
	}
}
