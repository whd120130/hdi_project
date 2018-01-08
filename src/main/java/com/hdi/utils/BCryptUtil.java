package com.hdi.utils;


import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by wanghuidong on 2018/1/5.
 */
public class BCryptUtil {

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean check(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }

}
