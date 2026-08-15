package lk.ijse.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lk.ijse.userservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(UserDTO userDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDTO.getRole());
        return doGenerateToken(claims, userDTO.getEmail());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // ✅ secure key

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS512) // ✅ correct signature
                .compact();
    }

}
