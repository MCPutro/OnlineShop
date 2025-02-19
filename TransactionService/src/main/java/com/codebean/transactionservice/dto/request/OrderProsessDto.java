package com.codebean.transactionservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProsessDto {
    @NotNull(message = "cannot be null")
    private Long orderId;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String orderStatus;
}
