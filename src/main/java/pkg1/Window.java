package pkg1;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private static Window window;
    public float r, g, b, a;
    private long glfwWindow; //this is a number that is a memory address to our window
    private ImGuiLayer imgui;

    private static Scene currentScene;

    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "2D Game Engine";
        this.r = 1.0f;
        this.b = 1.0f;
        this.g = 1.0f;
        this.a = 1.0f;
    }
    public static void changeScene(int newScene){
        switch(newScene){
            case 0:
                currentScene = new LevelEditorScene();
                break;
            case 1:
                currentScene = new LevelScene();
                break;

            default:
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
        currentScene.load();
        currentScene.init();
        currentScene.start();
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


        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        this.imgui = new ImGuiLayer(glfwWindow);
        this.imgui.initImGui();

        Window.changeScene(0);
    }
    public void loop(){
        float beginTime = (float)glfwGetTime();
        float endTime;
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

            this.imgui.update(dt, currentScene);
            glfwSwapBuffers(glfwWindow);

            endTime = (float) glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
        currentScene.saveExit();
    }
    public static int getWidth(){
        return get().width;
    }
    public static int getHeight(){
        return get().height;
    }
    public static void setWidth(int newWidth){
        get().width = newWidth;
    }

    public static void setHeight(int newHeight){
        get().height = newHeight;
    }
}
