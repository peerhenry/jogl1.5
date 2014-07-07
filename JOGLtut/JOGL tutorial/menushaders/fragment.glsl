#version 400
#extension GL_ARB_explicit_uniform_location : enable

in vec2 UV;

out vec4 FragColor;

uniform sampler2D Tex;

void main()
{
	FragColor = texture(Tex, UV);
}