package com.ammaPaasam.unavagam.auth;

import com.ammaPaasam.unavagam.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwtConfig {

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(User user){
        return  Jwts.builder().setSubject(user.getId().toString()).claim("role",user.getRole()).claim("email",user.getEmail()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)).signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
