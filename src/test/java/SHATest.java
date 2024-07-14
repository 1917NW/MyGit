import utils.SHAUtil;

public class SHATest {
    public static void main(String[] args) {
        String s = SHAUtil.encodeTo40Bits("hello, lxy".getBytes());
        System.out.println("Hash值为:" + s);
        System.out.println("Hash长度为："+ s.length());
    }
}
