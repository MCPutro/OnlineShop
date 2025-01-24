package com.codebean.UserService.utils;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 23 Jan 2025 17:32
@Last Modified 23 Jan 2025 17:32
Version 1.0
*/

import com.codebean.UserService.model.Permissions;
import com.codebean.UserService.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {
    private String secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9x"; // Gantilah dengan key yang lebih aman
    private int expirationInMilliSeconds = 1000 * 60 * 60; // 1 detik * 60 * 60 = 1 jam
    private Map<String, Object> JwtResponse = new HashMap<>();

    // Fungsi untuk mendapatkan kunci yang digunakan untuk memverifikasi tanda tangan token
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Fungsi untuk mendapatkan kunci yang digunakan untuk memverifikasi tanda tangan token
    private SecretKey getSigningKey2() {
        return new SecretKeySpec(secretKey.getBytes(), "HMACSHA256");
    }

//    // Generate JWT token
//    public String generateToken(String username, List<String> permission) {
//        return Jwts.builder()
//                .subject(username)
//                .claim("permission", String.join(" ", permission))
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + expirationInMilliSeconds)) // 1 jam
//                .signWith(getSigningKey2())
//                .compact();
//    }

    // Generate JWT token
    public String newGenerateToken(String username, Long userId, List<String> permission) {

        return Jwts.builder()
                .subject(username)
                .claim(JwtConstants.PERMISSIONS, String.join(" ", permission))
                .claim(JwtConstants.USERID, userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationInMilliSeconds)) // 1 jam
                .signWith(getSigningKey2())
                .compact();
    }

    // Fungsi untuk memvalidasi token JWT
    public Map<String, Object> validateToken(String token) {
        this.JwtResponse.clear();
        try {
            // Mengurai token JWT dan memverifikasi tanda tangan
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey2())// Tentukan kunci untuk memverifikasi token
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            ;

            // Cek apakah token sudah kedaluwarsa
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
//                System.out.println("Token sudah kedaluwarsa.");
//                return false;
                this.JwtResponse.put(JwtConstants.VALID, false);
                this.JwtResponse.put(JwtConstants.MESSAGE, "Token sudah kedaluwarsa.");
                return this.JwtResponse;
            }

//            //cek user
//            if(!claims.getSubject().equals(subject)){
//                System.out.println("subject gak cocok.");
//                return false;
//            }

//            set subject, permission, userid
            this.JwtResponse.put(JwtConstants.SUBJECT, claims.getSubject());
            this.JwtResponse.put(JwtConstants.PERMISSIONS, claims.get(JwtConstants.PERMISSIONS));
            this.JwtResponse.put(JwtConstants.USERID, claims.get(JwtConstants.USERID));

//            Object permission = claims.get("permission");
//            System.out.println("JwtUtil() " + permission);

            this.JwtResponse.put(JwtConstants.VALID, true);
//            Jika token valid dan tidak kedaluwarsa, kembalikan true
//            return true;
            return this.JwtResponse;
        } catch (SignatureException e) {
            this.JwtResponse.put(JwtConstants.VALID, false);
            this.JwtResponse.put(JwtConstants.MESSAGE, "Tanda tangan token tidak valid.");
        } catch (MalformedJwtException e) {
            this.JwtResponse.put(JwtConstants.VALID, false);
            this.JwtResponse.put(JwtConstants.MESSAGE, "Token tidak valid.");
        } catch (ExpiredJwtException e) {
            this.JwtResponse.put(JwtConstants.VALID, false);
            this.JwtResponse.put(JwtConstants.MESSAGE, "Token sudah kedaluwarsa.");
        } catch (UnsupportedJwtException e) {
            this.JwtResponse.put(JwtConstants.VALID, false);
            this.JwtResponse.put(JwtConstants.MESSAGE, "Token tidak didukung.");
        } catch (IllegalArgumentException e) {
            this.JwtResponse.put(JwtConstants.VALID, false);
            this.JwtResponse.put(JwtConstants.MESSAGE, "Token kosong atau tidak valid.");
        }

        return this.JwtResponse;
    }


}
