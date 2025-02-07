package com.codebean.UserService.filter;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 23 Jan 2025 17:34
@Last Modified 23 Jan 2025 17:34
Version 1.0
*/

import com.codebean.UserService.service.AuthUserDetailService;
import com.codebean.UserService.utils.Constants;
import com.codebean.sharemodule.Jwt.JwtConstants;
import com.codebean.sharemodule.Jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        try {
            if (token != null ) {
                Map<String, Object> jwtClaims = this.jwtUtil.validateToken(token);
                if (jwtClaims != null && jwtClaims.get(JwtConstants.VALID).equals(true)) {
                    final UserDetails userDetails = this.authUserDetailService.loadUserByUsername(jwtClaims.get(JwtConstants.SUBJECT).toString());
                    final UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails.getUsername(),
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // menambahkan userid ke request
                    request.setAttribute(Constants.USER_ID, jwtClaims.get(JwtConstants.USERID).toString());

                    // Menambahkan detail request ke objek Authentication
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Menambahkan authentication ke security context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    System.out.println("JWT Claim : " + jwtClaims);
                }
            }
        } catch (Exception ex) {
            // ganti jadi log aja
            ex.printStackTrace();
        }

        // Lanjutkan ke filter berikutnya dalam chain
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}