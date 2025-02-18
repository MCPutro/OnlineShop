package com.codebean.websiteui.dto.client.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

    @NonNull
    @Min(value = 1, message = "must selected address")
    private Long addressId;

    @NonNull
    @Size(min = 1, message = "must have at least one cart item.")
    private List<Long> cartIds;
}
