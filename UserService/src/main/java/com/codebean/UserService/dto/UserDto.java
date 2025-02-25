package com.codebean.UserService.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private Boolean isActive;

    private Boolean isDelete;

    private String role;

    private String name;
}
