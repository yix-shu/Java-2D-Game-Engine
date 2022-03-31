package pkg1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import renderer.Renderer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected  GameObject activeGO = null;
    protected boolean levelLoaded = false;

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
    public void sceneImgui(){
        if (activeGO != null){
            ImGui.begin("Inspector");
            activeGO.imgui(); //game object we have selected or am inspecting
            ImGui.end();
        }
        imgui();
    }

    public void imgui(){

    }
    public void saveExit(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();
        try{
            FileWriter writer = new FileWriter("level.txt");
            writer.write(gson.toJson(this.gameObjects));
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();
        String inFile = "";
        try{
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!inFile.equals("")){
            GameObject[] objs = gson.fromJson(inFile, GameObject[].class);
            for (int i = 0; i < objs.length; i ++){
                addGameObject(objs[i]); //adding deserialized game objects to scene
            }
            this.levelLoaded = true;
        }
    }
}

