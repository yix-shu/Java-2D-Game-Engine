package pkg1;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import renderer.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene{

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
            //vertices or position             //color
            0.5f, -0.5f, 0.0f,                 1.0f, 0.0f, 0.0f, 1.0f, //Bottom right
            -0.5f, 0.5f, 0.0f,                 0.0f, 1.0f, 0.0f, 1.0f, //Top left
            0.5f, 0.5f, 0.0f,                  0.0f, 0.0f, 1.0f, 1.0f, //Top right
            -0.5f, -0.5f, 0.0f,                1.0f, 1.0f, 0.0f, 1.0f, //Bottom right
    };

    //In counter-clockwise order
    private int[] elementArray = {
            /*
                     x           x



                     x           x
             */
            2, 1, 0, //Top right triangle
            0, 1, 3  //Bottom left triangle
    };
    private int vaoID, vboID, eboID;
    private Shader defaultShader;

    public LevelEditorScene(){
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();
    }

    @Override
    public void init(){
        this.camera = new Camera(new Vector2f()); //setting up camera at 0, 0
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
        int vertexSizeBytes = (positionSize + colorSize) * floatSizeBytes;
        //defining the pattern in which the positions appear in
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        //defining the pattern in which the color data appear in
        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize*floatSizeBytes);
        glEnableVertexAttribArray(1);

    }

    @Override
    public void update(float dt) {

        defaultShader.use();
        defaultShader.uploadMat4("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4("uView", camera.getViewMatrix());
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
    }
}
