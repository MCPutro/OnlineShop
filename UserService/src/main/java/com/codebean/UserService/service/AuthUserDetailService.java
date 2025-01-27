package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 23 Jan 2025 17:35
@Last Modified 23 Jan 2025 17:35
Version 1.0
*/

import com.codebean.UserService.dto.response.UserLoginRespDto;
import com.codebean.UserService.dto.response.UserRegRespDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Permissions;
import com.codebean.UserService.model.User;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.utils.Constants;
import com.codebean.sharemodule.Jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;


@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> registerUser(User user, HttpServletRequest request) {
        String encode = this.passwordEncoder.encode(user.getUsername() + user.getPassword());

        user.setPassword(encode);

        return this.userService.save(user, request);
    }

    @Transactional
    public ResponseEntity<Object> loginUser(User user, HttpServletRequest request) {
        try {
            //find user by username
            Optional<User> optionalUserByUsername = this.userRepository.findFirstByUsername(user.getUsername());
            if (!optionalUserByUsername.isPresent()) {
                return Response.unauthorized(Constants.WRONGUSERNAMEORPASSWORD, "FEAUT0010", request);
            }

            User userDB = optionalUserByUsername.get();

            if (!this.passwordEncoder.matches((user.getUsername() + user.getPassword()), userDB.getPassword())) {
                return Response.unauthorized(Constants.WRONGUSERNAMEORPASSWORD, "FEAUT0010", request);
            }

            if (!userDB.getIsActive()) {
                return Response.unauthorized(Constants.ACCOUNTISNOTACTIVE, "FEAUT0020", request);
            }

            String token = this.jwtUtil.generateToken(userDB.getUsername(), userDB.getID(),
                    userDB.getPermissions().stream()
                            .map(Permissions::getName).toList()
            );

            UserLoginRespDto userLoginRespDto = new UserLoginRespDto();
            BeanUtils.copyProperties(userDB, userLoginRespDto);
            userLoginRespDto.setToken(token);
            userLoginRespDto.setId(userDB.getID());
            userLoginRespDto.setRole(userDB.getRole().getName());

            return Response.success(Constants.LOGINSUCCESS, userLoginRespDto, request);
        } catch (Exception e) {

            return Response.internalServerError(e.getMessage(), "FEAUT0030", request);
        }


    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findFirstByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User userDB = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                userDB.getUsername(),
                userDB.getPassword(),
                userDB.getAuthorities()
        );
    }
}
