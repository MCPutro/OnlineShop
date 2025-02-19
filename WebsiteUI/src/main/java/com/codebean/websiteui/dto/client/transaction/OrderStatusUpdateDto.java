package com.codebean.websiteui.dto.client.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateDto {

    @NotNull(message =  "cannot be null")
    private Long orderId;

    @NotEmpty(message =  "cannot be empty")
    @NotBlank(message =  "cannot be blank")
    @NotNull(message =  "cannot be null")
    private String orderStatus;
}
