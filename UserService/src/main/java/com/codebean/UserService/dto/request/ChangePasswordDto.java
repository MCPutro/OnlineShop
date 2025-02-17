package com.codebean.UserService.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    @NotNull(message = "cannot be null")
    private Long userId;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Size(min = 8, max = 50, message = "must contain 8-50 character")
    private String currentPassword;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Size(min = 8, max = 50, message = "must contain 8-50 character")
    private String newPassword;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Size(min = 8, max = 50, message = "must contain 8-50 character")
    private String confirmPassword;

}
