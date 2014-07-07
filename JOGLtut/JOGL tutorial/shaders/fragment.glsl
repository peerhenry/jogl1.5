#version 400
#extension GL_ARB_explicit_uniform_location : enable

in vec3 Color;
in vec2 Position;
out vec3 FragColor;

void main()
{
	if(Position.x < 0.1 && Position.x > -0.1 && Position.y < 0.1 && Position.y > -0.1) FragColor = vec3(1,1,0);
	else FragColor = Color;
}