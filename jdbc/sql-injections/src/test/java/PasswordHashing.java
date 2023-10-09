import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHashing {

    public static void main(String[] args) {
        String[] passwords = {"admin123", "kasun123", "nuwan123"};
        for (String password : passwords) {
            String sha512 = DigestUtils.sha256Hex(password);
            System.out.printf("%s: %s \n", password, sha512);
        }
    }
}
