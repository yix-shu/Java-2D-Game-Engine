package pkg1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

import java.util.Objects;

/*
import components.FontRenderer;
import components.SpriteRenderer;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
*/
public class LevelEditorScene extends Scene{
    /*
    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "void main(){\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "\n" +
            "}";
    private String fragShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main(){\n" +
            "    color = fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderProgram;
    private float[] vertexArray = {
            //vertices or position             //color                       //UV Coordinates
            100.5f, 0.5f, 0.0f,                 1.0f, 0.0f, 0.0f, 1.0f,      1, 1,//Bottom right
            0.5f, 100.5f, 0.0f,                 0.0f, 1.0f, 0.0f, 1.0f,      0, 0,//Top left
            100.5f, 100.5f, 0.0f,               0.0f, 0.0f, 1.0f, 1.0f,      1, 0,//Top right
            0.5f, 0.5f, 0.0f,                   1.0f, 1.0f, 0.0f, 1.0f,      0, 1 //Bottom right
    };

    //In counter-clockwise order
    private int[] elementArray = {
            /*
                     x           x



                     x           x
             */
    /*
            2, 1, 0, //Top right triangle
            0, 1, 3  //Bottom left triangle
    };
    private int vaoID, vboID, eboID;
    private Shader defaultShader;
    private Texture testTexture; //for demo purposes

    GameObject testObj; //for testing
    private boolean firstTime = false;
    */

    private GameObject obj1;
    private Spritesheet sprites;

    public LevelEditorScene(){
        /*
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();
         */

    }

    @Override
    public void init(){
        /*
        System.out.println("Creating 'test object'");
        this.testObj = new GameObject("test object");
        this.testObj.addComponent(new SpriteRenderer());
        this.testObj.addComponent( new FontRenderer());
        this.addGameObject(this.testObj);

        this.camera = new Camera(new Vector2f()); //setting up camera at 0, 0
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();
        this.testTexture = new Texture("assets/images/testImage2.jpg");
        //===========================================================
        //Generate VAO, VBO, and EBO buffer objects, and send to GPU
        //===========================================================

        vaoID = glGenVertexArrays(); //creating a new vertex array in GPU and returns unique ID
        glBindVertexArray(vaoID); //makes sure everything we do below is to this vertex array

        //Create a float buffer (must be passed bc expected by OpenGL) of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        //Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID); //makes sure we are acting on this buffer

        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW); //sends buffer data and affirms data will not be changed, so static

        //Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW); //sending buffer created

        //Add attribute pointers, tells the GPU which are positions and which are colors
        int positionSize = 3;
        int colorSize = 4;
        int floatSizeBytes = 4;
        int uvSize = 2; //2 UV's

        int vertexSizeBytes = (positionSize + colorSize + uvSize) * Float.BYTES;
        //defining the pattern in which the positions appear in
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        //defining the pattern in which the color data appear in
        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize*Float.BYTES);
        glEnableVertexAttribArray(1);

        //UV Coordinates
        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize)*Float.BYTES);
        glEnableVertexAttribArray(2);
        */

        loadResources();
        this.camera = new Camera(new Vector2f());
        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        /*int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2); //width of scene
        float totalHeight = (float)(300 - yOffset * 2); //height of scene

        //dividing size of screen into 100 squares
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight/ 100.0f;

        for (int x = 0; x < 100; x ++){
            for (int y = 0; y < 100; y++){
                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Obj" + x + "" + y, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos/ totalHeight, 1, 1)));

                this.addGameObject(go);
            }
        }*/
        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), -1);
        obj1.addComponent(new SpriteRenderer(
                new Sprite(AssetPool.getTexture("assets/images/blendImage1.png"))
        ));
        this.addGameObject(obj1);


        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 2);
        obj2.addComponent(new SpriteRenderer(
                new Sprite(AssetPool.getTexture("assets/images/blendImage2.png"))
        ));
        this.addGameObject(obj2);
        this.activeGO = obj1;

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        gson.toJson();

    }
    private void loadResources(){
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpriteSheet("assets/images/spritesheet.png", new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"), 16, 16, 26, 0));
    }
    /*
    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
     */
    @Override
    public void update(float dt) {
        /*
        //to test camera movement
        camera.position.x -= dt * 50.0f;
        camera.position.y -= dt * 50.0f;

        defaultShader.use();
        //upload texture to shader
        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0); //activates texture at slot 0
        testTexture.bind(); //binds texture to the slot

        defaultShader.uploadMat4("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4("uView", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", Time.getTime()); //allows us to do time-based animations

        //Bind the VAO that we're using
        glBindVertexArray(vaoID);

        //Enable the vertex atribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        //specifies the shape we are drawing, the type, and where we start
        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        //unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        defaultShader.detach();
        glUseProgram(0); //use program nothing
        if (!firstTime){
            System.out.println("Creating game object.");
            GameObject go = new GameObject("Game Test 2");
            go.addComponent(new SpriteRenderer());
            this.addGameObject(go);
            firstTime = true;
        }

        spriteFlipTimeLeft -= dt;
        if (spriteFlipTimeLeft <= 0){
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex ++;
            if (spriteIndex > 4){
                spriteIndex = 0;
            }
            obj1.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }
        */
        for (GameObject go: this.gameObjects){
            go.update(dt);
        }
        this.renderer.render();
    }
    @Override
    public void imgui(){
        ImGui.begin("Test window");
        ImGui.text("Random texto");
        ImGui.end();
    }
}
