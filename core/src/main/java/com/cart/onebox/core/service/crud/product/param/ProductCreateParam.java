package com.cart.onebox.core.service.crud.product.param;

import com.cart.onebox.core.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateParam {
    private Product product;
}
