package com.hzslb.securitydemo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 多重加密————迭代SHA算法+密钥+随机盐来对密码加密
 */
public class StandardPasswordEncoderTest {
    private static final String SITE_WIDE_SECRET = "github";
    private static final PasswordEncoder encoder = new StandardPasswordEncoder(
            SITE_WIDE_SECRET);

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }
    public static void main(String[] args) {
        System.out.println(StandardPasswordEncoderTest.encrypt("admin"));
        System.out.println(StandardPasswordEncoderTest.encrypt("admin"));
        System.out.println(StandardPasswordEncoderTest.encrypt("admin"));
        System.out.println(StandardPasswordEncoderTest.encrypt("admin"));
        System.out.println(StandardPasswordEncoderTest.encrypt("admin"));
        System.out.println(StandardPasswordEncoderTest.match("admin",StandardPasswordEncoderTest.encrypt("admin")));
    }
}
