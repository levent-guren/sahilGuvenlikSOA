package tr.gov.sg.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import tr.gov.sg.entity.Role;
import tr.gov.sg.entity.Users;

@Component
public class Security implements CommandLineRunner {
	@Value("${SECRET_KEY:9fFOrPaLSEFm82T63KXveVbNnJgxMoLu0v0mLGGzsbrks4Eo+uNKVzmrU8eduTj036dPI1yK5Q+1KiQYoPcyqQ==}")
	private String SECRET_KEY;
	
	public String tokenUret(Users user) {
		JwtBuilder builder = Jwts.builder();
		List<Role> roller = user.getRoles();
		String roles = roller.stream().map(Role::getName).reduce((a, b) -> a + "," + b).orElse("");
		Map<String, Object> customKeys = new HashMap<>();
		customKeys.put("roller", roles);
		// customKeys.put("kullaniciId", kullanici.getId().toString());
		customKeys.put("kullaniciAdi", user.getUsername());
		builder = builder.claims(customKeys);
		Instant tarih = Instant.now().plus(1, ChronoUnit.DAYS);
		builder = builder.subject("login").issuedAt(new Date()).expiration(Date.from(tarih));
		return builder.signWith(getKey()).compact();
	}


	private SecretKey getKey() {
		byte[] decoded = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decoded);
	}
	public static void keyUret() {
		SecretKey key = Jwts.SIG.HS512.key().build();
		String str = Encoders.BASE64.encode(key.getEncoded());
		System.out.println(str);
	}

	@Override
	public void run(String... args) throws Exception {
		//keyUret();
	}



}
