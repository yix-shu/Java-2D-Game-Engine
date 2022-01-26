package pkg1;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
    }
    public void adjustProjection(){
        projectionMatrix.identity(); //creates an identity matrix
        //creating an ortho matrix that gives us 0.0f -> 40f (32x32) units to the right and then
        // 0.0f -> 21.0f (32x32) units to the top
        //allows us to view objects from 0 to 100 units away (zNear and zFar)
        projectionMatrix.ortho(0.0f, 32.0f * 40.0f, 0.0f, 32.0f*21.0f, 0.0f, 100.0f);
    }
}
