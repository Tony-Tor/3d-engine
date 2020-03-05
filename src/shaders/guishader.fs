#version 330 core

out vec4 FragColor;
in vec2 TexCoord;

uniform sampler2D texture;

void main()
{
	vec3 FragPos = texture2D(texture,TexCoord).xyz;
	float a = texture2D(texture,TexCoord).a;
	
	FragColor = vec4(FragPos, a);
}