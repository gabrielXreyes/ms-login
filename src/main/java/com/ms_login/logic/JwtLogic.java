package com.ms_login.logic;

import com.ms_login.entity.User;
import com.ms_login.services.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class JwtLogic implements JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;

    @Value("${security.jwt.refresh-token-expiration-time}")
    private Long jwtExpirationRefresh;

    @Override
    public String generateToken(final User user) {
        return BuildToken(user,jwtExpiration);
    }

    @Override
    public String generateRefreshToken(final User user) {
        return BuildToken(user,jwtExpirationRefresh);
    }

    @Override
    public String BuildToken(User user, long time) {
        return Jwts.builder()
                .id(user.getUsername())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ time))
                .signWith(getSingKey())
                .compact();
    }

    //Generate secret key
    private SecretKey getSingKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}


