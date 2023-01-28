package model;

import com.google.gson.Gson;

public class BuildModelJSON {
    public static String parseJSONString(PostBody postBody){
        if(postBody == null){
            throw new IllegalArgumentException("Post Body is not valid !");
        }
        Gson gson = new Gson();
        return gson.toJson(postBody);
    }
}
