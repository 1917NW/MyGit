package utils;

import java.io.*;
import java.util.zip.InflaterInputStream;


public class ZipUtil {

    public static String unZipFile(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            InflaterInputStream iis = new InflaterInputStream(fis);
            InputStreamReader isr = new InputStreamReader(iis);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        }catch (Exception e){
            System.out.println("UnZip failed!");
        }
        return stringBuilder.toString();
    }
}
