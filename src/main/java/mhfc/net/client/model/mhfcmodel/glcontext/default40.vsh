#version 410
layout (location = 0) in Point {
	vec4 position; // location 0
	vec4 normal; // location 1
	vec2 texcoords; // location 2
	uvec4 bindingIndices; // location 3
	vec4 bindingValues; // location 4
};

struct Bone { //size 16
	mat3 rotationMatrix; //size 12
	vec3 offset; //size 4
};

// Layout std140 guarantees sizeof(Bone)==16
layout (std140, binding = 0) uniform BonesStatic {
	Bone bones[];
};
// Layout std140 guarantees sizeof(mat4)==16
layout (std140, binding = 1) uniform BonesTransform {
	mat4 transformMatrix[];
};

void main()
{
	gl_Position = position;
	gl_TexCoord[0].uv = texcoords;
}