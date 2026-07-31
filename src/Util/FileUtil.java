package Util; // 파일 읽기/쓰기

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    // 파일 저장
    public static void writeFile(String path, String data) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일 읽기
    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}