package com.codebean.transactionservice.filter;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 31 Jan 2025 11:25
@Last Modified 31 Jan 2025 11:25
Version 1.0
*/

import com.codebean.sharemodule.Jwt.JwtConstants;
import com.codebean.sharemodule.Jwt.JwtUtil;
import com.codebean.transactionservice.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private AuthUserDetailService authUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        try {
            if (token != null) {
                Map<String, Object> jwtClaims = this.jwtUtil.validateToken(token);
                if (jwtClaims != null && jwtClaims.get(JwtConstants.VALID).equals(true)) {
                    String username = jwtClaims.get(JwtConstants.SUBJECT).toString();
                    String[] s = jwtClaims.get(JwtConstants.PERMISSIONS).toString().split(" ");
                    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                    for (String permission : s) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(permission));
                    }
//                    System.out.println(">>"+username);
//                    System.out.println(">>"+s);

//                    final UserDetails userDetails = this.authUserDetailService.loadUserByUsername(jwtClaims.get(JwtConstants.SUBJECT).toString());
                    final UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    grantedAuthorities
                            );

                    // menambahkan userid ke request
                    request.setAttribute(Constants.USER_ID, jwtClaims.get(JwtConstants.USERID).toString());

                    // Menambahkan detail request ke objek Authentication
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Menambahkan authentication ke security context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
//                    System.out.println("JWT Claim : " + jwtClaims);
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
