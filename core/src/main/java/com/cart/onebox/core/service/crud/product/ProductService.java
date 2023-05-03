package com.cart.onebox.core.service.crud.product;

import com.cart.onebox.core.service.crud.product.param.ProductCreateParam;
import com.cart.onebox.core.service.crud.product.param.ProductDetailParam;
import com.cart.onebox.core.service.crud.product.result.ProductCreateResult;
import com.cart.onebox.core.service.crud.product.result.ProductListResult;
import com.cart.onebox.core.service.crud.product.result.ProductDetailResult;

public interface ProductService {
    ProductListResult getList();
    ProductDetailResult getProductDetail(ProductDetailParam param);
    ProductCreateResult createProduct(ProductCreateParam param);
}
