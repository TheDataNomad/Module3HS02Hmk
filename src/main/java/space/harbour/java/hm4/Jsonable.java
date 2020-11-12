package space.harbour.java.hm4;

import javax.json.JsonObject;

public interface Jsonable {
    public String toJsonString();

    public JsonObject toJsonObject();

    //public fromJsonString();
    //public fromJsonObject();

}
