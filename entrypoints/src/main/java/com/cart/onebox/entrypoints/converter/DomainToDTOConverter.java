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
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainToDTOConverter {
    ProductDTO convert(Product product);
    CartDTO convert(Cart cart);
    ProductListRS convert(ProductListResult productListUseCaseResult);
    ProductDTO convert(ProductDetailResult productDetailUseCaseResult);
    ProductCreateRS convert(ProductCreateResult productCreateUseCaseResult);
    CartListRS convert(CartListResult cartListUseCaseResult);
    CartDTO convert(CartDetailResult cartListUseCaseResult);
    CartCreateRS convert(CartCreateResult cartCreateUseCaseResult);

}
