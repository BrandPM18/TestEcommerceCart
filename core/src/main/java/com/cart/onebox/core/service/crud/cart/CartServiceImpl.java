package com.cart.onebox.core.service.crud.cart;

import com.cart.onebox.core.StatusCode;
import com.cart.onebox.core.dataproviders.CartRepository;
import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.domain.Cart;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.cart.param.CartCreateParam;
import com.cart.onebox.core.service.crud.cart.param.CartUpdateParam;
import com.cart.onebox.core.service.crud.cart.result.CartCreateResult;
import com.cart.onebox.core.service.crud.cart.result.CartDeleteResult;
import com.cart.onebox.core.service.crud.cart.result.CartDetailResult;
import com.cart.onebox.core.service.crud.cart.result.CartListResult;
import com.cart.onebox.core.service.crud.cart.result.CartUpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartListResult getList() {
        CartListResult result = new CartListResult();
        try {
            List<Cart> cartList = cartRepository.findAll();
            result.setCarts(cartList);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CartListResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }

        return result;
    }

    @Override
    public CartDetailResult getDetail(String id){
        CartDetailResult result = new CartDetailResult();
        try {
            Cart cart = cartRepository.findById(id);
            result.setCart(cart);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CartDetailResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }

        return result;
    }

    @Override
    public CartCreateResult createCart(CartCreateParam param) {
        CartCreateResult result = new CartCreateResult();
        UUID id = UUID.randomUUID();
        param.getCart().setId(id.toString());
        try {
            param.getCart().setTotalPrice(calculateTotalPrice(param.getCart().getProductList()));
            cartRepository.save(param.getCart());
            result.setId(id);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CartCreateResult.builder()
                    .message(e.getMessage())
                    .statusCode(StatusCode.ERROR)
                    .build();
        }

        return result;
    }

    @Override
    public CartUpdateResult updateCart(CartUpdateParam cart) {
        CartUpdateResult result = new CartUpdateResult();
        try {
            Cart cartFromDB = cartRepository.findById(cart.getCart().getId());
            if (cartFromDB == null) {
                throw new Exception("CartServiceImpl: Cart not found");
            }

            if(cart.isAddToCart()) {
                List<Integer> newProductListIds = setProductList(cart.getCart().getProductList(),cartFromDB.getProductList());
                cart.getCart().setProductList(newProductListIds);
                cart.getCart().setProducts(newProductListIds.toString());
                cart.getCart().setTotalPrice(calculateTotalPrice(newProductListIds));
                cartRepository.update(cart.getCart());
            } else {
                cartFromDB.getProductList().removeAll(cart.getCart().getProductList());
                cartFromDB.setTotalPrice(calculateTotalPrice(cartFromDB.getProductList()));
                cartFromDB.setProducts(cartFromDB.getProductList().toString());
                cartFromDB.setModificationUser(cart.getCart().getModificationUser());
                cartRepository.update(cartFromDB);
            }

            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CartUpdateResult.builder()
                    .message(e.getMessage())
                    .statusCode(StatusCode.ERROR)
                    .build();
        }
        return result;
    }

    @Override
    public CartDeleteResult deleteCart(String id) {
        CartDeleteResult result = new CartDeleteResult();
        try {
            cartRepository.deleteById(id);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return (CartDeleteResult) CartDeleteResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }
        return result;
    }

    @Override
    public void cleanExpired() {
        List<Cart> cartList = cartRepository.findAll();
        for (Cart cart : cartList) {
            if (cart.isExpired()) {
                LOGGER.info("CartServiceImpl: Cart expired, deleting cart with id: " + cart.getId());
                cartRepository.deleteById(cart.getId());
            }
        }
    }

    private float calculateTotalPrice(List<Integer> productListIds) throws Exception {
        float totalPrice = 0;
        for (Integer productId : productListIds) {
            Product product = productRepository.findById(productId);
            if (product == null) {
                throw new Exception("CartServiceImpl: Product not found");
            }
            totalPrice += product.getAmount();
        }
        return totalPrice;
    }

    //funcion de java para agregar un List<Integer> a otro sin repeticion
    private List<Integer> setProductList(List<Integer> list1, List<Integer> list2) {
        List<Integer> list3 = new ArrayList<>() {{
            addAll(list1);
            addAll(list2);
        }};
        return list3.stream().distinct().collect(Collectors.toList());
    }
}
