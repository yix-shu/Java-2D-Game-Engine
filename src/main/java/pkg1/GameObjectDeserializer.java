package pkg1;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GameObjectDeserializer implements JsonDeserializer<GameObject> {

    @Override
    public GameObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        //retrieving all data in the serialized game object
        String name = jsonObject.get("name").getAsString();
        JsonArray components = jsonObject.getAsJsonArray("components");
        Transform transform = context.deserialize(jsonObject.get("transform"), Transform.class);
        int zindex = context.deserialize(jsonObject.get("zIndex"), int.class);

        //rebuilding the game object using the deserialized data
        GameObject go = new GameObject(name, transform, zindex);
        for (JsonElement e: components){
            Component c = context.deserialize(e, Component.class);
            go.addComponent(c);
        }
        return go;
    }
}
