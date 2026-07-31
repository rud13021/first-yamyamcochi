package Util; // 객체 <-> JSON 변환

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void saveJson(String path, Object data) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            System.out.println("JSON 저장 실패: " + e.getMessage());
        }
    }

    public static <T> T loadJson(String path, Type type) {
        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("JSON 불러오기 실패: " + e.getMessage());
            return null;
        }
    }
}