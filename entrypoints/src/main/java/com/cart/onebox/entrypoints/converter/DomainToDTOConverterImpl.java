package com.cart.onebox.entrypoints.converter;

import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.cart.result.CartCreateResult;
import com.cart.onebox.core.service.crud.cart.result.CartDetailResult;
import com.cart.onebox.core.service.crud.cart.result.CartListResult;
import com.cart.onebox.core.service.crud.product.result.ProductCreateResult;
import com.cart.onebox.core.service.crud.product.result.ProductDetailResult;
import com.cart.onebox.core.service.crud.product.result.ProductListResult;
import com.cart.onebox.entrypoints.model.CartDTO;
import com.cart.onebox.entrypoints.model.response.CartCreateRS;
import com.cart.onebox.entrypoints.model.response.CartListRS;
import com.cart.onebox.entrypoints.model.response.ProductCreateRS;
import com.cart.onebox.entrypoints.model.ProductDTO;
import com.cart.onebox.entrypoints.model.response.ProductListRS;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DomainToDTOConverterImpl implements DomainToDTOConverter {
    @Override
    public ProductDTO convert(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getAmount())
                .build();
    }

    @Override
    public CartDTO convert(Cart cart) {
        return CartDTO.builder()
                .id(UUID.fromString(cart.getId()))
                .creationUser(cart.getCreationUser())
                .creationDate(cart.getCreationDate())
                .modificationDate(cart.getModificationDate())
                .modificationUser(cart.getModificationUser())
                .totalPrice(cart.getTotalPrice())
                .productListIds(cart.castProductList())
                .build();
    }

    @Override
    public ProductDTO convert(ProductDetailResult productDetailUseCaseResult) {
        return ProductDTO.builder()
                .id(productDetailUseCaseResult.getProduct().getId())
                .description(productDetailUseCaseResult.getProduct().getDescription())
                .price(productDetailUseCaseResult.getProduct().getAmount())
                .build();
    }

    @Override
    public CartDTO convert(CartDetailResult cartListUseCaseResult) {
        return CartDTO.builder()
                .id(UUID.fromString(cartListUseCaseResult.getCart().getId()))
                .creationUser(cartListUseCaseResult.getCart().getCreationUser())
                .creationDate(cartListUseCaseResult.getCart().getCreationDate())
                .modificationDate(cartListUseCaseResult.getCart().getModificationDate())
                .modificationUser(cartListUseCaseResult.getCart().getModificationUser())
                .totalPrice(cartListUseCaseResult.getCart().getTotalPrice())
                .productListIds(cartListUseCaseResult.getCart().getProductList())
                .build();
    }

    @Override
    public CartCreateRS convert(CartCreateResult cartCreateUseCaseResult) {
        return CartCreateRS.builder()
                .id(cartCreateUseCaseResult.getId())
                .build();
    }

    @Override
    public ProductListRS convert(ProductListResult productListUseCaseResult) {
        return ProductListRS.builder()
                .products(List.of(productListUseCaseResult.getProducts().stream().map(this::convert).toArray(ProductDTO[]::new)))
                .build();
    }
    @Override
    public ProductCreateRS convert(ProductCreateResult productCreateUseCaseResult) {
        return ProductCreateRS.builder()
                .id(productCreateUseCaseResult.getId())
                .build();
    }

    @Override
    public CartListRS convert(CartListResult cartListUseCaseResult) {
        return CartListRS.builder()
                .carts(List.of(cartListUseCaseResult.getCarts().stream().map(this::convert).toArray(CartDTO[]::new)))
                .build();
    }
}
