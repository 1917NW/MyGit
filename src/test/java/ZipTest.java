import utils.ZipUtil;

import java.io.File;

public class ZipTest {
    public static void main(String[] args) {
        File file = new File("/Users/lxy/Documents/study/codecrafters-git-java/src/test/resources/1.txt");
        ZipUtil.zipStrContent(file, "hello, lxy");
        String s = ZipUtil.unZipFile(file);
        System.out.println("解压后内容为:" + s);

    }
}
