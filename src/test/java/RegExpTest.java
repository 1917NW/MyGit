public class RegExpTest {
    public static void main(String[] args) {
        String content = "11\0hello world";
        System.out.println(content.indexOf('\0'));
        String[] split = content.split("\0");

        System.out.println(split[0]);
        System.out.println(split[1]);



    }
}
