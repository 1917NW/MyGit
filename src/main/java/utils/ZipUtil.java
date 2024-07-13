package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    public static String unZipFile(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                stringBuilder.append(buffer);
            }

            System.out.println("Blob file unzipped successfully!");
        } catch (IOException e) {
            System.out.println("Blob file unzipped failed!");
        }
        return stringBuilder.toString();
    }
}
