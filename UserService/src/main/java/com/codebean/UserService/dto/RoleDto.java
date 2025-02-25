package com.codebean.UserService.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private LocalDateTime updatedDate;
    private String updatedBy;
}
