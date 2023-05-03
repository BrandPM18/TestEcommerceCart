package com.cart.onebox.core.service.crud.cart.result;

import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.service.crud.DefaultResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailResult extends DefaultResult {
    private Cart cart;
}
