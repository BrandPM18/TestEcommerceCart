package com.cart.onebox.entrypoints.converter;

import com.cart.onebox.core.service.crud.cart.param.CartCreateParam;
import com.cart.onebox.core.service.crud.cart.param.CartUpdateParam;
import com.cart.onebox.core.service.crud.product.param.ProductCreateParam;
import com.cart.onebox.entrypoints.model.request.CartRQ;
import com.cart.onebox.entrypoints.model.request.CartUpdateRQ;
import com.cart.onebox.entrypoints.model.request.ProductRQ;

public interface DTOToDomainConverter {

    ProductCreateParam convert(ProductRQ productRQ);
    CartCreateParam convert(CartRQ cartRQ);
    CartUpdateParam convert(CartUpdateRQ cartRQ, String id);
}
