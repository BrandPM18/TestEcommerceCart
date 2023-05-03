package com.cart.onebox.core.service.crud.cart.result;

import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.service.crud.DefaultResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListResult extends DefaultResult {
    private List<Cart> carts;
}
