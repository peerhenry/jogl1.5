#version 400
#extension GL_ARB_explicit_uniform_location : enable

layout(location=0) in vec2 VertexPosition;
layout(location=1) in vec3 VertexColor;

out vec3 Color;
out vec2 Position;

void main()
{
	Color = vec3(1-VertexColor.r, 1-VertexColor.g, 1-VertexColor.b);
	Position = VertexPosition;
	gl_Position = vec4(Position, 0, 1.0);
}