package renderer;

import org.lwjgl.BufferUtils;
import org.w3c.dom.Text;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

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
    private String filepath;
    private int texID;

    public Texture(String filepath){
        this.filepath = filepath;

        //Generate texture on GPU
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        //Set texture parameters
        //Repeats image in both directions
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT); //right
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT); //left

        //When stretching image, pixelate don't blur
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); //Chooses nearest pixel and turns into a bigger pixel
        //when shrinking an image, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath, width, height, channels, 0); //loads image and stores data

        if (image != null){
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0), 0,
                    GL_RGB, GL_UNSIGNED_BYTE, image); //uploads image to the GPU
        } else{
            assert false : "Error: (Texture) Could not load image '" + filepath + "'";
        }

        stbi_image_free(image); //frees memory to prevent memory leak
    }
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, texID);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
