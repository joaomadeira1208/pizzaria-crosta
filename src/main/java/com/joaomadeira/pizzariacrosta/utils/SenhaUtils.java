package com.joaomadeira.pizzariacrosta.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encode(String senha) {
        return ENCODER.encode(senha);
    }

    public static boolean matches(String senha, String senhaCriptografada) {
        return ENCODER.matches(senha, senhaCriptografada);
    }

}
