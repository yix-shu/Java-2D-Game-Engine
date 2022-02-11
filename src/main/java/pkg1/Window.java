package pkg1;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private static Window window;
    public float r, g, b;
    private long glfwWindow; //this is a number that is a memory address to our window

    private static Scene currentScene;

    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "2D Game Engine";
        this.r = 1.0f;
        this.b = 1.0f;
        this.g = 1.0f;
    }
    public static void changeScene(int newScene){
        switch(newScene){
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                currentScene.start();
                break;

            default:
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
    }
    public static Window get(){
        if (Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public static Scene getScene(){
        return get().currentScene;
    }
    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
        loop();

        //Memory release
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public void init(){
        //Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW to set up our windows
        if (!GLFW.glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configure GLFW
        GLFW.glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //Create window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW window");
        }
        //set up all callbacks
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
        //Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        //Enable v-sync (no restriction), which is buffer swapping
        glfwSwapInterval(1); //swaps every single frame, no wait time

        //Make window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities(); //makes sure we can use the bindings
        Window.changeScene(0);
    }
    public void loop(){
        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)){
            //Poll events
            //System.out.println("Running at " + (1.0f/dt)); //just looking at framerate
            glfwPollEvents();
            glClearColor(r, g, b, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT); //flushes clear color to entire screen
            if (dt >= 0){
                currentScene.update(dt);
            }

            /*
            hmmm
            //Set up test to see if event listeners are working
            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
                System.out.println("Space key has been pressed");
            }
            */
            glfwSwapBuffers(glfwWindow);
            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
