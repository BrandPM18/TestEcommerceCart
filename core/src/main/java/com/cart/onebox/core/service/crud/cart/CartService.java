package com.cart.onebox.core.service.crud.cart;

import com.cart.onebox.core.service.crud.cart.param.CartCreateParam;
import com.cart.onebox.core.service.crud.cart.param.CartUpdateParam;
import com.cart.onebox.core.service.crud.cart.result.CartCreateResult;
import com.cart.onebox.core.service.crud.cart.result.CartDeleteResult;
import com.cart.onebox.core.service.crud.cart.result.CartDetailResult;
import com.cart.onebox.core.service.crud.cart.result.CartListResult;
import com.cart.onebox.core.service.crud.cart.result.CartUpdateResult;

public interface CartService {
    CartListResult getList();
    CartDetailResult getDetail(String id);
    CartCreateResult createCart(CartCreateParam cart);
    CartUpdateResult updateCart(CartUpdateParam cart);
    CartDeleteResult deleteCart(String id);
    void cleanExpired();
}
