package util;

import components.Sprite;
import components.Spritesheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<>();

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
    public static void addSpriteSheet(String resourceName, Spritesheet spritesheet){
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())){
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }
    public static Spritesheet getSpritesheet(String resourceName){
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())){
            assert false : "Error: Tried to access spritesheet '" + resourceName + "' and it has not been added to Asset Pool.";
        }
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }
}
