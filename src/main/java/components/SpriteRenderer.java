package components;

import org.joml.Vector2f;
import org.joml.Vector4f;
import pkg1.Component;
import pkg1.Transform;
import renderer.Texture;

public class SpriteRenderer extends Component {

    private Vector4f color;
    private Sprite sprite;

    private Transform lastTransform;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
    }

    public SpriteRenderer(Sprite sprite){
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void start(){
        this.lastTransform = gameObject.transform.copy();
    }
    public Vector4f getColor() {
        return this.color;
    }
    public Texture getTexture(){
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords(){
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setColor(Vector4f color){
        this.color.set(color);
    }
}
