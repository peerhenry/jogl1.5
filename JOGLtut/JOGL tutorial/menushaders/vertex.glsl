#version 400
#extension GL_ARB_explicit_uniform_location : enable

layout(location=0) in vec2 VertexPosition;
layout(location=1) in vec2 TexCoord;

out vec2 UV;

void main()
{
	UV = TexCoord;
	gl_Position = vec4(VertexPosition, 0, 1.0);
}