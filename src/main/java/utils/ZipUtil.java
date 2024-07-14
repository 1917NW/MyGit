package utils;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
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
            e.printStackTrace();
            System.out.println("UnZip failed!");
        }
        return stringBuilder.toString();
    }

    /**
     * 将strContent压缩后的内容存储到file中
     * @param file
     * @param strContent
     */
    public static void zipStrContent(File file, byte[] strContent){
        try {
            System.out.println("压缩内容到文件"+file.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(file);
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            dos.write(strContent);
            dos.close();
            fos.close();
        }catch (Exception e){
            System.out.println("Zip Failed!");
        }

    }

    public static void zipStrContent(File file, String strContent){
        zipStrContent(file, strContent.getBytes());
    }
}
