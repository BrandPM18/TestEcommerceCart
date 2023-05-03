package com.cart.onebox.dataproviders.repository.impl;

import com.cart.onebox.core.dataproviders.CartRepository;
import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.dataproviders.repository.mappers.CartMapper;

import java.util.List;

public class CartRepositoryImpl implements CartRepository {

        private CartMapper cartMapper;

        public CartRepositoryImpl(CartMapper cartMapper) {
            this.cartMapper = cartMapper;
        }

        @Override
        public List<Cart> findAll() {
            return cartMapper.findAll();
        }

        @Override
        public Cart findById(String id) {
            Cart cartFromDB = cartMapper.findById(id);
            cartFromDB.setProductList(cartFromDB.castProductList());
            return cartFromDB;
        }

        @Override
        public int save(Cart cart) {
            return cartMapper.save(cart);
        }

        @Override
        public int update(Cart cart) {
            return cartMapper.update(cart);
        }
        @Override
        public int deleteById(String id) {
            return cartMapper.deleteById(id);
        }
}
