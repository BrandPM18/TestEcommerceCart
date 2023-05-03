package com.cart.onebox.core.service.crud.cart.param;


import com.cart.onebox.core.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateParam {
    private Cart cart;
    private boolean addToCart;
}
