package com.cart.onebox.entrypoints.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRQ {

    @Schema(description = "List of products in the cart", example = "[1,2,3]", required = true)
    private List<Integer> productListIds;

    @Schema(example = "user", description = "User who created the cart")
    private String creationUser;
}
