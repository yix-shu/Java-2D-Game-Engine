package pkg1;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Rigidbody extends Component{
    private int colliderType = 0;
    private float friction = 0.08f;
    public Vector3f velocity = new Vector3f(0, 0.5f, 0);
    public transient Vector4f temp = new Vector4f(0, 0, 0,0);
}
