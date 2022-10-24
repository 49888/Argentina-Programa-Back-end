package main.utils;

import com.google.gson.Gson;


public class GsonUtil {
    
    public static String serialize(Object src){

        Gson gson = new Gson();

        return gson.toJson(src);
    }

    public static <D> D toObject(String json, Class<D> classOfT){

        Gson gson = new Gson();

        return gson.fromJson(json, classOfT);
    }

    public static <D> D toObject(Object src, Class<D> classOfT){

        Gson gson = new Gson();

        String json = gson.toJson(src);

        return gson.fromJson(json, classOfT);
    }
}

