package com.ezentwix.teamcostco.config;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    // 비밀번호 해시화
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 비밀번호 검증
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}


