package Util; // 객체 <-> JSON 변환

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import DTO.User;

public class JsonUtil {
    private static Gson gson = new Gson();

    // 객체 → JSON
    public static String toJson(ArrayList<User> users) {
        return gson.toJson(users);
    }

    // JSON → 객체
    public static ArrayList<User> fromJson(String json) {
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        return gson.fromJson(json, type);
    }
}