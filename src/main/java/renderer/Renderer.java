package renderer;

import components.SpriteRenderer;
import pkg1.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer(){
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go){
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        if (spr != null){
            add(spr);
        }
    }
    private void add(SpriteRenderer sprite){
        boolean added = false;
        for (RenderBatch batch : batches){
            if (batch.hasRoom() && batch.zIndex() == sprite.gameObject.getzIndex()){ //adds only sprites with the same zIndex to the same batch
                Texture tex = sprite.getTexture();
                if (batch.hasTexture(tex) || batch.hasTextureRoom() || tex == null){
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.getzIndex());
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            Collections.sort(batches); //sorts batches every time they are added.
        }
    }
    public void render(){
        for (RenderBatch batch : batches){
            batch.render();
        }
    }
}
