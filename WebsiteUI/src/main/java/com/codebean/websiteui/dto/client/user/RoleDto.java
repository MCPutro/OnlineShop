package com.codebean.websiteui.dto.client.user;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private List<PermissionDto> permissions;
}
