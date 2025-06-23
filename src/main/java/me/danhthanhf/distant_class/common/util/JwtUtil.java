package me.danhthanhf.distant_class.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.danhthanhf.distant_class.config.JwtProperties;
import me.danhthanhf.distant_class.entity.User;
import me.danhthanhf.distant_class.security.UserDetailImpl;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for handling JWT operations
 * such as token creation, validation, and parsing.
 *
 * @author ThanhND
 * @since 2025-06-20
 */
@Component
public class JwtUtil {

    // Constants for JWT claims
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_ROLE = "roles";
    private final Key signingKey;
    private final long expirationTime;
    private final long verifyEmailExpirationTime;

    public JwtUtil(JwtProperties jwtProperties) {
        this.signingKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        this.expirationTime = jwtProperties.getExpiration();
        this.verifyEmailExpirationTime = jwtProperties.getVerifyEmailExpiration();
    }

    // generates access token
    public String generateToken(User user) {
        Map<String, Object> claims = Map.of(
                CLAIM_USER_ID, user.getId(),
                CLAIM_ROLE, user.getRoles()
        );
        return buildToken(user.getEmail(), claims, expirationTime);
    }

    // Generates a token for email verification, reset password, etc.s
    public String generateVerifyEmailToken(User user) {
        return buildToken(user.getEmail(), null, verifyEmailExpirationTime);
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = parseClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // get principal from token to authentication & authorize user
    public UserDetailImpl extractUserPrincipal(String token) {
        return extractClaim(token, claims -> UserDetailImpl.builder()
                .id(claims.get(CLAIM_USER_ID, Long.class))
                .roles(claims.get(CLAIM_ROLE, List.class))
                .email(claims.getSubject())
                .build()
        );
    }

    // Helper method to build token with specific expiration time
    private String buildToken(String subject, Map<String, Object> claims, long expirationTime
    ) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date((System.currentTimeMillis() + expirationTime)))
                .signWith(signingKey)
                .compact();
    }
}
