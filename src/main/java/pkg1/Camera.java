package pkg1;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }
    public void adjustProjection(){
        projectionMatrix.identity(); //creates an identity matrix
        //creating an ortho matrix that gives us 0.0f -> 40f (32x32) units to the right and then
        // 0.0f -> 21.0f (32x32) units to the top
        //allows us to view objects from 0 to 100 units away (zNear and zFar)
        projectionMatrix.ortho(0.0f, 32.0f * 40.0f, 0.0f, 32.0f*21.0f, 0.0f, 100.0f);
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f); //sets up which location is front
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f); //which direction is up
        this.viewMatrix.identity();
        this.viewMatrix = viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f),
                cameraFront.add(position.x, position.y, 0.0f),
                cameraUp);

        return this.viewMatrix;
    }
    public Matrix4f getProjectionMatrix(){
        return this.projectionMatrix;
    }
}
