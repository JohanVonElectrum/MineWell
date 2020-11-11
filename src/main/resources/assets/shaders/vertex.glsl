#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec3 color;

uniform mat4 worldMatrix;
uniform mat4 projectionMatrix;

out vec4 vertexColor;

void main() {
    gl_Position = projectionMatrix * worldMatrix * vec4(position, 1.0f);
    vertexColor = vec4(color, 1.0f);
}
