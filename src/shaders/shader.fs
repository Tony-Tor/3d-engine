#version 330 core

layout (location = 0) out vec4 position_texture;
layout (location = 1) out vec4 normal_texture;
layout (location = 2) out vec4 specular_texture;

uniform sampler2D diffuse_map;
uniform sampler2D normal_map;
uniform sampler2D specular_map;

in vec2 TextureCoord;
in vec3 FragPos;
in vec3 Normal;

void main(void) {
	position_texture = vec4(FragPos, 0.0);
	normal_texture = vec4(normalize(Normal), 0.0);
	specular_texture.rgb = texture2D(diffuse_map,TextureCoord).rgb;
	specular_texture.a = texture2D(specular_map,TextureCoord).r;	
}

