package Repository; // Util에 저장 요청

import java.util.ArrayList;

import DTO.User;
import Util.FileUtil;
import Util.JsonUtil;

public class UserRepository {

    private static final String PATH = "lib/users.json";

    // 저장
    public static void saveUsers(ArrayList<User> users) {
        String json = JsonUtil.toJson(users);
        FileUtil.writeFile(PATH, json);
    }


    // 불러오기
    public static ArrayList<User> loadUsers() {
        String json = FileUtil.readFile(PATH);

        if(json.isEmpty())
            return new ArrayList<>();
        return JsonUtil.fromJson(json);
    }
}
