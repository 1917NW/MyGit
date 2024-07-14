package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtil {

    public static byte[] readFileToBytes(String filePath){
        return readFileToBytes(new File(filePath));
    }

    public static byte[] readFileToBytes(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error:" + "读取文件" + file.getAbsolutePath() + "报错");
        }
        return new byte[0];

    }
}
