package tr.gov.sg.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class Security {
	@Value("${SECRET_KEY:9fFOrPaLSEFm82T63KXveVbNnJgxMoLu0v0mLGGzsbrks4Eo+uNKVzmrU8eduTj036dPI1yK5Q+1KiQYoPcyqQ==}")
	private String SECRET_KEY;

	public Claims validateTokenAndGetClaims(String token) {
		JwtParser parser = Jwts.parser().verifyWith(getKey()).build();
		// parseSignClaims metodu hem SECRET_KEY kontrolünü yapıyor,
		// hem de token'ın zamanı geçmiş mi kontrolünü yapıyor.
		// Herhangi bir hata durumunda da exception fırlatıyor.

		return parser.parseSignedClaims(token).getPayload();
	}

	private SecretKey getKey() {
		byte[] decoded = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decoded);
	}

}
