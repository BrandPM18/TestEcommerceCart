package com.cart.onebox.entrypoints.converter;

import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.cart.param.CartCreateParam;
import com.cart.onebox.core.service.crud.cart.param.CartUpdateParam;
import com.cart.onebox.core.service.crud.product.param.ProductCreateParam;
import com.cart.onebox.entrypoints.model.request.CartRQ;
import com.cart.onebox.entrypoints.model.request.CartUpdateRQ;
import com.cart.onebox.entrypoints.model.request.ProductRQ;

public class DTOToDomainConverterImpl implements DTOToDomainConverter {

    @Override
    public ProductCreateParam convert(ProductRQ productRQ) {
        return ProductCreateParam.builder()
                .product(Product.builder()
                        .description(productRQ.getDescription())
                        .amount(productRQ.getPrice())
                        .build())
                .build();
    }
    @Override
    public CartCreateParam convert(CartRQ cartRQ) {
        return CartCreateParam.builder()
                .cart(Cart.builder()
                        .creationUser(cartRQ.getCreationUser())
                        .products(cartRQ.getProductListIds().toString())
                        .productList(cartRQ.getProductListIds())
                        .build())
                .build();
    }

    @Override
    public CartUpdateParam convert(CartUpdateRQ cartRQ, String id) {
        return CartUpdateParam.builder()
                .addToCart(cartRQ.isAddToCart())
                .cart(Cart.builder()
                        .id(id)
                        .modificationUser(cartRQ.getModificationUser())
                        .products(cartRQ.getProductListIds().toString())
                        .productList(cartRQ.getProductListIds())
                        .build())
                .build();
    }
}
