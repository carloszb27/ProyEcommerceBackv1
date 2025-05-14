package com.proyectos.ProyectoEcommerce.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private String TIME_EXPIRATION;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(TIME_EXPIRATION)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
             getAllClaims(token);
             return true;
        } catch (Exception e) {
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }
    }

    // Valida el token
//    public boolean validateToken (String token, UserDetails userDetails){
//        final String username = getUserName(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

    // Obtener el username del token
    public String getUserName(String token){
        return getClaim(token, Claims::getSubject);
    }

    // Obtener todos los claims del token
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Obtener la firma del token
    private Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    // El token esta expirado
//    private boolean isTokenExpired(String token) {
//        return getExpiration(token).before(new Date());
//    }
//
//    // Obtiene la expiracion
//    private Date getExpiration(String token) {
//        return getClaim(token, Claims::getExpiration);
//    }
}
