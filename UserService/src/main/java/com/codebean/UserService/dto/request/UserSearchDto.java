package com.codebean.UserService.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {
    private String username;
    private String email;
    private String name;
    private String role;
    private Boolean status;
}
