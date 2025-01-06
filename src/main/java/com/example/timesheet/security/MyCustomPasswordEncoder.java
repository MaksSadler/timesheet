//package com.example.timesheet.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//@Component
//public class MyCustomPasswordEncoder implements PasswordEncoder {
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        // md5, sh256 ...
//        return "hashed_" + rawPassword.toString();
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return Objects.equals(encode(rawPassword), encodedPassword);
//    }
//}
