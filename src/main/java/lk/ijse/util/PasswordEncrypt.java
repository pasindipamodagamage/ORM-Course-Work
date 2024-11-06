package lk.ijse.util;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypt {
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
          String hashedPassword = BCrypt.hashpw(password, salt);
          return hashedPassword;
    }
}
