package pkg1;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene{
    private boolean changingScene = false;
    private float timeRequired = 2.0f;
    public LevelEditorScene(){
        System.out.println("Inside level editor scene");
    }

    @Override
    public void update(float dt) {
        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }
        if (changingScene && timeRequired > 0){
            timeRequired -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        } else if (changingScene){
            Window.changeScene(1);
        }
    }
}
