//Graphic Library Shader Library
#type vertex
#version 330 core

//GL's vertex attrib pointer (vector, location, etc)
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCoords;
layout (location=3) in float aTexId;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 fColor;
out vec2 fTexCoords;
out float fTexId;

void main(){
    fColor = aColor;
    fTexCoords = aTexCoords;
    fTexId = aTexId;

    gl_Position = uProjection * uView * vec4(aPos, 1.0); //uses the camera matrices
}
#type fragment
#version 330 core

in vec4 fColor;
in vec2 fTexCoords;
in float fTexId;

uniform sampler2D uTextures[8];

out vec4 color;


void main(){
    //float noise = fract(sin(dot(fColor.xy, vec2(12.9898, 78.233)))*43758.5453);
    //color = fColor * noise; //creates a noise block for texture
    color = fColor*texture(uTextures[fTexId], fTexCoords);
}
