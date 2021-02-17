package com.sf.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        //password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("sf"));
        System.out.println(encoder.matches("sf", "$2a$10$GwmzDXZPkPeFUR3F5TmWEObV6thPZoZnkmBEEvRhB7A765F5jXWCm"));

        //client secret
        System.out.println(encoder.encode("secret"));
        System.out.println(encoder.matches("secret", "$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm"));
        System.out.println(encoder.matches("secret", "$2a$10$rYtvVQtTpr21NWeeqrPa0elm77A0ojzrDfWL.3DPG6mtcqoJQvmb."));

    }
}
