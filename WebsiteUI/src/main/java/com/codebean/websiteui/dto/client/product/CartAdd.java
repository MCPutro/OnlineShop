package com.codebean.websiteui.dto.client.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartAdd {
    @NotNull(message = "cannot be null")
    @Min(value = 1, message = "invalid product id")
    private Long productId;

    @NotNull(message = "cannot be null")
    @Min(value = 1, message = "min quantity is 1")
    private Integer quantity;
}
