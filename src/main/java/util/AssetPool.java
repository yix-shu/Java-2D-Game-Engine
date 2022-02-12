package util;

import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    public static Shader getShader(String resourceName){
        File file = new File(resourceName);
        if (shaders.containsKey(file.getAbsolutePath())){ //checks if shader exists in asset pool
            return shaders.get(file.getAbsolutePath());
        } else{
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaders.put(file.getAbsolutePath(), shader); //adds shader into asset pool
            return shader;
        }
    }
    public static Texture getTexture (String resourceName){
        File file = new File(resourceName);
        if (textures.containsKey(file.getAbsolutePath())){ //checks if shader exists in asset pool
            return textures.get(file.getAbsolutePath());
        } else{
            Texture texture = new Texture(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(),texture); //adds shader into asset pool
            return texture;
        }
    }
}
