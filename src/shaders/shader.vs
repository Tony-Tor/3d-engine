#version 330 core

in vec3 in_Position;
in vec3 in_Normal;
in vec2 in_TextureCoord;

out vec2 TextureCoord;
out vec3 FragPos;
out vec3 Normal;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main(void) {
	vec4 world_pos = modelMatrix * vec4(in_Position,1.0f);
	FragPos = world_pos.xyz;
	gl_Position = projectionMatrix * viewMatrix * world_pos;
	
	TextureCoord = in_TextureCoord;
	
	mat3 normal_matrix = transpose(inverse(mat3(modelMatrix)));
	Normal = normal_matrix * in_Normal;
}
