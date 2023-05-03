package com.cart.onebox.entrypoints.model.response;


import com.cart.onebox.entrypoints.model.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartListRS {
    private List<CartDTO> carts;
}
