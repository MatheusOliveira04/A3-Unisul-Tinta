package git.matheusoliveira04.api.store.configs.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    public static final String secret = "oratoroeuaroupadoreideromaarainhamatouorato";

    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    public Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {

        try {

            extractAllClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}