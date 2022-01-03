package br.com.authenticator.middleware;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.context.annotation.Bean;

public class HashPassword {

	@Bean
	public String hashGenerator(String password) {
		SimpleHash hash = new SimpleHash("md5", password);
		return hash.toHex();
	}
}