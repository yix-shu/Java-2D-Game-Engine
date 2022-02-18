package components;

import renderer.Texture;

import java.util.ArrayList;
import java.util.List;

public class Spritesheet {
    private Texture texture;
    private List<Sprite> sprites;

    public Spritesheet(Texture texture, int spriteWidth, int spriteHeight, int numSprites, int spacing){
        this.sprites = new ArrayList<>();
        this.texture = texture;
        int currentX = 0;
        int currentY = texture.getHeight() - spriteHeight;

        for (int i = 0; i < numSprites; i++){
            float topY = 0;
            float rightX = 0;
            float leftX = 0;
            float bottomY = 0;
        }
    }


}
