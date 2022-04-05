package pkg1;





import imgui.ImGui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class Component {

    public transient GameObject gameObject = null;
    public void update(float dt){

    }
    public void start(){

    }
    public void imgui(){
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field: fields){
                boolean isPrivate = Modifier.isPrivate(field.getModifiers());
                if (isPrivate){
                    field.setAccessible(true);
                }
                Class type = field.getType();
                Object value = field.get(this);
                String name = field.getName();

                if (type == int.class){
                    int val = (int)value;
                    int[] imInt = {val};
                    if (ImGui.dragInt(name + ": ", imInt)){ //creates a drag int for the variable
                        field.set(this, imInt[0]);
                    }
                } else if (type == float.class){
                    float val = (float)value;
                    float[] imFloat = {val};
                    if (ImGui.dragFloat(name + ": ", imFloat)){
                        field.set(this, imFloat[0]);
                    }
                } else if (type == boolean.class){
                    boolean val = (boolean) value;
                    boolean[] imBool = {val};
                    if (ImGui.checkbox(name + ": ", val)){
                        val = !val;
                        field.set(this, !val);
                    }
                }
                if (isPrivate){
                    field.setAccessible(false);
                }
            }
        } catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
