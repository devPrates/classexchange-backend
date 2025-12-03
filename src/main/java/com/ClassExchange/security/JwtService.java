package com.ClassExchange.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final KeyPair keyPair;

    public JwtService() {
        this.keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    }

    public String generateToken(String subject, Map<String, Object> claims, long expiresInSeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expiresInSeconds)))
                .signWith(privateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    public Jws<Claims> validate(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey())
                .build()
                .parseClaimsJws(token);
    }

    private PrivateKey privateKey() {
        return keyPair.getPrivate();
    }

    private PublicKey publicKey() {
        return keyPair.getPublic();
    }
}

