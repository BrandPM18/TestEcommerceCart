package com.cart.onebox.crud.cart;

import com.cart.onebox.core.dataproviders.CartRepository;
import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.service.crud.cart.CartServiceImpl;
import com.cart.onebox.core.service.crud.cart.param.CartCreateParam;
import com.cart.onebox.core.service.crud.cart.param.CartUpdateParam;
import com.cart.onebox.core.service.crud.cart.result.CartCreateResult;
import com.cart.onebox.core.service.crud.cart.result.CartDeleteResult;
import com.cart.onebox.core.service.crud.cart.result.CartDetailResult;
import com.cart.onebox.core.service.crud.cart.result.CartListResult;
import com.cart.onebox.core.service.crud.cart.result.CartUpdateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardServiceTest {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() throws IOException {
        cartRepository = Mockito.mock(CartRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
    }

    @Test
    void testCreateOk() {
        CartServiceImpl cartService = new CartServiceImpl(
                cartRepository,
                productRepository
        );
        CartCreateParam param = CartCreateParam.builder()
                .cart(Cart.builder()
                        .productList(new ArrayList<>())
                        .build())
                .build();
        CartCreateResult result = cartService.createCart(param);
    }

    @Test
    void testGetAllOk() {
        CartServiceImpl cartService = new CartServiceImpl(
                cartRepository,
                productRepository
        );
        Mockito.when(cartRepository.findAll()).thenReturn(List.of(mockCart()));

        CartListResult result = cartService.getList();
    }

    @Test
    void testGetIdOk() {
        CartServiceImpl cartService = new CartServiceImpl(
                cartRepository,
                productRepository
        );
        Mockito.when(cartRepository.findById(Mockito.anyString())).thenReturn(mockCart());

        String id = "123";
        CartDetailResult result = cartService.getDetail(id);
    }
    @Test
    void testUpdateCartOk() {
        CartServiceImpl cartService = new CartServiceImpl(
                cartRepository,
                productRepository
        );
        CartUpdateParam param = CartUpdateParam.builder()
                .addToCart(true)
                .cart(Cart.builder()
                        .productList(List.of(1))
                        .build())
                .build();

        CartUpdateResult result = cartService.updateCart(param);
    }

    @Test
    void testDeleteCartOk() {
        CartServiceImpl cartService = new CartServiceImpl(
                cartRepository,
                productRepository
        );
        String id = "123";
        CartDeleteResult result = cartService.deleteCart(id);
    }

    private Cart mockCart() {
        return Cart.builder()
                .id(UUID.randomUUID().toString())
                .productList(new ArrayList<>())
                .creationUser("test")
                .creationDate(LocalDateTime.now())
                .build();
    }


}