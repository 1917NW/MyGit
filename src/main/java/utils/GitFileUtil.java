package utils;

import java.io.File;
import java.io.IOException;

public class GitFileUtil {

    public static String writeGitObjectToFile(String content){
        // 通过SHA算法计算40位的HASH值
        String shaHash = SHAUtil.encodeTo40Characters(content.getBytes());

        String hashDir = shaHash.substring(0, 2);
        String hashFile = shaHash.substring(2);

        File root = new File(".git/objects");

        // 创建Hash目录
        File hashDirFile = new File(root,  hashDir);
        hashDirFile.mkdirs();

        // 创建文件
        File blobFile = new File(hashDirFile, hashFile);
        System.out.println(blobFile.getAbsolutePath());
        try {
            blobFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 写入zip压缩后的字节流
        ZipUtil.zipStrContent(blobFile, content);
        return shaHash;

    }
}
