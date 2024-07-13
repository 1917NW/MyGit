import java.io.File;

public class FileTest {

    public static void main(String[] args) {
        final File root = new File(".git");
        File bloShaFile = new File(root, "objects/" + 1 + "/" + 2);

        System.out.println(bloShaFile.getAbsolutePath());
    }
}
