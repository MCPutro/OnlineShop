package com.codebean.websiteui.dto.client.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateDto {
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String roleName;

    @JsonProperty("permissionIds")
    @NotNull(message = "cannot be null")
    @Size(min = 1, message = "must have at least one selected permission.")
    private List<Integer> permissions;
}
