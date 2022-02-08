package pkg1;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Camera camera;
    private boolean isRunning = false;
    private List<GameObject> gameObjects = new ArrayList<>();


    public Scene(){

    }
    public void init(){

    }
    public void start(){
        for (GameObject go: gameObjects){
            go.start();
        }
    }
    public void addGameObject(GameObject go){
        if (!isRunning){
            gameObjects.add(go);
        }
    }

    public abstract void update(float dt);
}
