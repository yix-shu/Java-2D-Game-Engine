package pkg1;

import org.joml.Vector2f;

public class Transform {

    public Vector2f position;
    public Vector2f scale;

    public Transform(){
        init(new Vector2f(), new Vector2f());
    }

    public Transform(Vector2f position){
        init(position, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale){
        init(position, scale);
    }

    public void init(Vector2f position, Vector2f scale){ //easier for overloading because this function can be called
        this.position = position;
        this.scale = scale;

    }

    public Transform copy(){
        Transform t = new Transform(new Vector2f(this.position), new Vector2f(this.scale));
        return t;
    }

    public void copy(Transform c){
        c.position.set(this.position);
        c.scale.set(this.scale);
    }
}
