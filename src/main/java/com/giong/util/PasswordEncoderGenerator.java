package com.giong.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	
	public static void main(String[] args) {
		int i = 0;
		while (i < 10) {
			final String password = "x";
			final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			final String hashedPassword = passwordEncoder.encode(password);
			
			System.out.println(hashedPassword);
			i++;
		}
	}
}
