package com.codebean.UserService.repository;

import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 16 Jan 2025 13:03
@Last Modified 16 Jan 2025 13:03
Version 1.0
*/

@SpringBootTest
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionOperations transactionOperations;


//    @Test
//    void insert() {
//        this.transactionOperations.executeWithoutResult(transactionStatus -> {
//            UserProfile updatedUser = UserProfile.builder()
//                    .createdBy("sistem")
//                    .firstName("Akshay")
//                    .lastName("Khan")
//                    .gender("Male")
//                    .dateOfBirth(new Date())
//                    .build();
//
//            Optional<User> userOptional = this.userRepository.findById(7L);
//
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                UserProfile existingProfile = user.getProfile();
//                if (existingProfile != null) {
//                    existingProfile.setFirstName(updatedUser.getFirstName());
//                    existingProfile.setLastName(updatedUser.getLastName());
//                    existingProfile.setDateOfBirth(updatedUser.getDateOfBirth());
//                    existingProfile.setGender(updatedUser.getGender());
//                    existingProfile.setProfilePicture(updatedUser.getProfilePicture());
//                    // Update other fields as needed
//
//                    UserProfile savedProfile = userProfileRepository.save(existingProfile);
//                    System.out.println("ada 1");
//                } else {
//                    //user Profile
//                    UserProfile userProfile = UserProfile.builder()
//                            .user(user)
//                            .firstName(updatedUser.getFirstName())
//                            .lastName(updatedUser.getLastName())
//                            .dateOfBirth(updatedUser.getDateOfBirth())
//                            .gender(updatedUser.getGender())
//                            .createdBy("sistem")
//                            .build();
//                    userProfile.setUser(user);
//                    userProfileRepository.save(userProfile);
//                    System.out.println("ada 0");
//                }
//            }
//        });
//    }


//    bisa
    @Test
    void name() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Optional<UserProfile> byId = this.userProfileRepository.findById(5L);
            if (byId.isPresent()) {
                UserProfile userProfile = byId.get();
                userProfile.setFirstName("Akshay");
                userProfile.setLastName("Khan");
                userProfile.setGender("Male");
                userProfile.setDateOfBirth(LocalDateTime.now());

                this.userProfileRepository.save(userProfile);
            }
        });

    }
}