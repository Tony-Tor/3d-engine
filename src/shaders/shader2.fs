#version 330 core

out vec4 FragColor;
in vec2 TexCoord;

uniform sampler2D position_texture;
uniform sampler2D normal_texture;
uniform sampler2D specular_texture;

void main()
{
	vec3 FragPos = texture2D(position_texture,TexCoord).xyz;
	vec3 Normal = texture2D(normal_texture,TexCoord).xyz;
	vec3 Diffuse = texture2D(specular_texture,TexCoord).xyz;
	float Specular = texture2D(specular_texture,TexCoord).a;
	
	vec3 lighting = Diffuse * 0.1;
	vec3 viewDir = normalize(vec3(0.0,0.0,0.0) - FragPos);
	
	vec3 position = vec3(0.0f,0.0f,-4.0f);
	vec3 color = vec3(1.0f, 1.0f, 1.0f);
	
	vec3 lightDir = normalize(position - FragPos);
	vec3 diffuse = max(dot(Normal, lightDir), 0.0f) * Diffuse * color;
	
	vec3 halfwayDir = normalize(lightDir + viewDir);
	float spec = pow(max(dot(Normal, halfwayDir), 0.0), 16.0);
	vec3 specular = color * spec * Specular;
	
	float distance = length(position - FragPos);
	float attenuation = 1.0/(1.0 + 0.7 * distance + 1.8 * distance * distance);
	
	//diffuse *= attenuation;
	
	lighting += diffuse + specular;
	
	FragColor = vec4(lighting, 1.0);
}