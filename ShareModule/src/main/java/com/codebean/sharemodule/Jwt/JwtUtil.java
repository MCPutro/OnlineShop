package com.codebean.sharemodule.Jwt;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 22 Jan 2025 13:42
@Last Modified 22 Jan 2025 13:42
Version 1.0
*/

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.util.*;

public class JwtUtil {
    private String secretKey;
    private Integer expirationInSeconds;
    private final Map<String, Object> JwtResponse = new HashMap<>();

    public JwtUtil() {
        Properties properties = new Properties();

        // Memuat file sharemodule-config.properties dari classpath
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("sharemodule-config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find sharemodule-config.properties");
                throw new IllegalArgumentException("sharemodule-sharemodule-config.properties not found in resources");
            }

//            URL resourceUrl = JwtUtil.class.getClassLoader().getResource("sharemodule-config.properties");
//            if (resourceUrl != null) {
//                System.out.println("Config file loaded from: " + resourceUrl);
//            } else {
//                System.out.println("Config file not found in classpath");
//            }

            // Memuat data properties dari file
            properties.load(input);

            // Mengakses data dari file properties
            this.secretKey = properties.getProperty("jwt.secretKey");

            String stringExpiredTimeInSeconds = properties.getProperty("jwt.expiredTimeInSeconds");

            if (stringExpiredTimeInSeconds == null || stringExpiredTimeInSeconds.isEmpty() || stringExpiredTimeInSeconds.equals("0")) {
                expirationInSeconds = 360;
            } else {
                expirationInSeconds = Integer.parseInt(stringExpiredTimeInSeconds);
            }

//            System.out.println("secretKey: " + secretKey);
//            System.out.println("Duration: " + (expirationInSeconds + 3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Fungsi untuk mendapatkan kunci yang digunakan untuk memverifikasi tanda tangan token
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Fungsi untuk mendapatkan kunci yang digunakan untuk memverifikasi tanda tangan token
    private SecretKey getSigningKey2() {
        return new SecretKeySpec(secretKey.getBytes(), "HMACSHA256");
    }

    // Generate JWT token
    public String generateToken(String username, Long userId, List<String> permission) {

        return Jwts.builder()
                .subject(username)
                .claim(JwtConstants.PERMISSIONS, String.join(" ", permission))
                .claim(JwtConstants.USERID, userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (expirationInSeconds * 1000) ))
                .signWith(getSigningKey2())
                .compact();
    }

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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getExpirationInSeconds() {
        return expirationInSeconds;
    }

    public void setExpirationInSeconds(Integer expirationInSeconds) {
        this.expirationInSeconds = expirationInSeconds;
    }

    public Map<String, Object> getJwtResponse() {
        return JwtResponse;
    }
}
