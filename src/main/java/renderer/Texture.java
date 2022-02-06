package renderer;
/*
Notes:

- Textures are just images
- uv coordinates: coordinates of each corner of the texture/image
- maps image to fit into pixels; stretches/compresses based on size of pixel
- Wrap to continue image/texture or to add textures next to a texture

Steps:
- Store texture into array [r1, g1, b1, a1, r2, g2, b2, a2, ...]
- List out parameters (pixelate, wrap, etc.) so that OpenGL knows what kind of image/texture
- Upload the 2D texture to the GPU using OpenGL

*/
public class Texture {
}
