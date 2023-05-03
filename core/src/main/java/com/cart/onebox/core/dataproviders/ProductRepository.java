package com.cart.onebox.core.dataproviders;

import com.cart.onebox.core.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(Integer id);
    Integer save(Product product);
}
