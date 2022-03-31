package components;

import org.joml.Vector2f;
import renderer.Texture;

public class Sprite {
    private Texture texture = null;
    private Vector2f[] texCoords = {
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1),
        };

//    public Sprite(Texture tex){
//        this.texture = tex;
//        Vector2f[] texCoords = {
//                new Vector2f(1, 1),
//                new Vector2f(1, 0),
//                new Vector2f(0, 0),
//                new Vector2f(0, 1),
//        };
//        this.texCoords = texCoords;
//    }
//    public Sprite(Texture tex, Vector2f[] texCoords){
//        this.texture = tex;
//        this.texCoords = texCoords;
//    }

    public Texture getTexture(){
        return this.texture;
    }
    public Vector2f[] getTexCoords(){
        return this.texCoords;
    }
    public void setTexture(Texture tex){
        this.texture = tex;
    }
    public void setTexCoords(Vector2f[] texCoords){
        this.texCoords = texCoords;
    }
}
