package com.cart.onebox.core.dataproviders;

import com.cart.onebox.core.domain.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> findAll();
    Cart findById(String id);
    int save(Cart cart);
    int update(Cart cart);
    int deleteById(String id);
}
