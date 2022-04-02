package pkg1;

import imgui.ImGui;

import java.lang.reflect.Field;

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
                Class type = field.getType();
                Object value = field.get(this);
                String name = field.getName();

                if (type == int.class){
                    int val = (int) value;
                    int[] imInt = {val};
                    if (ImGui.dragInt(name + ": ", imInt)){ //creates a drag int for the variable
                        field.set(this, imInt[0]);
                    }
                }
            }
        } catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
