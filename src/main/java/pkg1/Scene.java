package pkg1;

import renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private Renderer renderer;
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();


    public Scene(){

    }
    public void init(){
    /*
    Initializing all the objects in the scene and the scene itself.
     */
    }
    public void start(){
        /*
        Starting the scene when the scene is initialized and ready
        and starting all the game objects
         */
        for (GameObject go: gameObjects){
            go.start();
            this.renderer.add(go);
        }
        isRunning = true;
    }
    public void addGameObject(GameObject go){
        /*
        Adding game objects to the scene.
         */
        if (!isRunning){ //checks if it is not running
            gameObjects.add(go);
        } else{ //checks if the scene is running and runs newly added game object
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera(){
        return this.camera;
    }
}
