package renderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Shader {

    private int shaderProgramID;
    private String vertexSource;
    private String fragmentSource;
    private String filepath;

    public Shader(String filepath){
        this.filepath = filepath;
        try {
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            int index = source.indexOf("#type") + 6; //looking for type (vertex or frag) after the word "#type "
            int eol = source.indexOf("\r\n", index); //finding end of line
            String firstPattern = source.substring(index, eol).trim(); //looking at the word returned (vertex or frag) after the word (#type )

            index = source.indexOf("#type", eol);
            eol = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index, eol).trim(); //looking at next type

            if (firstPattern.equals("vertex")){
                vertexSource = splitString[1]
            } else if (firstPattern.equals("fragment")){
                fragmentSource = splitString[1];
            } else{
                throw new IOException("Unexpected token '" + firstPattern + "'");
            }

            if (secondPattern.equals("vertex")){
                vertexSource = splitString[1]
            } else if (secondPattern.equals("fragment")){
                fragmentSource = splitString[1];
            } else{
                throw new IOException("Unexpected token '" + secondPattern + "'");
            }

            System.out.println(vertexSource);
            System.out.print(fragmentSource);

        } catch(IOException e){
            e.printStackTrace();
            assert false : "Error: Could not open file for shader: '" + filepath + "'";
        }
    }

    public void compile(){

    }

    public void use(){

    }

    public void detach(){

    }
}
