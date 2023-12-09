package com.example.task_management_system.jwt;

import com.example.task_management_system.exception.EmailNotFoundException;
import com.example.task_management_system.exception.TokenException;
import com.example.task_management_system.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.task_management_system.entity.User;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    @Value("${taskman.duration}")
    private int duration;
    private final SecretKey secretKey;

    private final UserRepository userRepository;

    public JwtService(@Value("${taskman.secret}") String secret, UserRepository userRepository) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userRepository = userRepository;
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(duration, ChronoUnit.DAYS)))
                .signWith(secretKey)
                .compact();
    }

    public User validateAndGetUser(String token) {
        User user;
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            user = userRepository.findByEmail(claims.getSubject()).orElseThrow(() -> new EmailNotFoundException("User with this email not found."));

            if (token.equals(user.getToken())) return user;
            else throw new TokenException("Invalid token.");

        } catch (Exception e) {
            throw new TokenException(e.getMessage());
        }
    }
}
