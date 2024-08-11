package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    public static String encodeTo40Characters(byte[] bytes){

        try {
            MessageDigest message = MessageDigest.getInstance("SHA-1");
            message.update(bytes);
            byte[] hashBytes = message.digest();
            return bytesToHexString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}
