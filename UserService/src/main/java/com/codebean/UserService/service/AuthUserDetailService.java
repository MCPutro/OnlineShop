package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 23 Jan 2025 17:35
@Last Modified 23 Jan 2025 17:35
Version 1.0
*/
/**
 * Platform Code : AUT
 * Modul Code : 05
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVAUT05001 -> [FV] [AUT] [05] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.sharemodule.Jwt.JwtUtil;
import com.codebean.UserService.dto.response.UserLoginResp;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.RolePermission;
import com.codebean.UserService.model.User;
import com.codebean.UserService.repository.RolePermissionRepository;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Transactional
    public ResponseEntity<?> registerUser(User user, HttpServletRequest request) {
        return this.userService.save(user, request);
    }

    @Transactional
    public ResponseEntity<?> login(User user, HttpServletRequest request) {
        try {
            Optional<User> optionalUser = this.userRepository.findFirstByUsername(user.getUsername());
            if (!optionalUser.isPresent()) {
                return Response.unauthorized(Constants.INVALID_USERNAME_OR_PASSWORD, "FVAUT05001", request);
            }

            //get user data from db
            User userDB = optionalUser.get();

            // matching password
            if (!this.passwordEncoder.matches((user.getUsername() + "." + user.getPassword()), userDB.getPassword())) {
                return Response.unauthorized(Constants.INVALID_USERNAME_OR_PASSWORD, "FVAUT05001", request);
            }

            //check if user is not active
            if (!userDB.getIsActive()) {
                return Response.unauthorized(Constants.ACCOUNT_IS_NOT_ACTIVE, "FVAUT05003", request);
            }

            List<String> activePermissions = this.getActivePermissionsByRole(userDB.getRole());

            //generate token jwt
            String token = this.jwtUtil.generateToken(userDB.getUsername(), userDB.getID(),
                    activePermissions
            );

            //build response message
            UserLoginResp userLoginResp = new UserLoginResp();
            BeanUtils.copyProperties(userDB, userLoginResp);
            userLoginResp.setToken(token);
            userLoginResp.setId(userDB.getID());
            userLoginResp.setRole(userDB.getRole().getName());

            return Response.success(Constants.LOGIN_SUCCESS, userLoginResp, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.LOGIN_FAIL, "FEAUT05003", request);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected List<String> getActivePermissionsByRole(Role role) {
        List<RolePermission> listActiveRolePermission = this.rolePermissionRepository.findByRoleAndIsActive(role, true);
        return listActiveRolePermission.stream().map(rolePermission -> rolePermission.getPermission().getName()).toList();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = this.userRepository.findFirstByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User userDB = optionalUser.get();

        List<String> activePermissionsByRole = this.getActivePermissionsByRole(userDB.getRole());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String permission : activePermissionsByRole) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        return new org.springframework.security.core.userdetails.User(
                userDB.getUsername(),
                userDB.getPassword(),
                grantedAuthorities
        );
    }
}
