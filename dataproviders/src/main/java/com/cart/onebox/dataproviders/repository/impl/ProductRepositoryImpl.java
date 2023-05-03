package com.cart.onebox.dataproviders.repository.impl;

import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.dataproviders.repository.mappers.ProductMapper;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private ProductMapper productMapper;

    public ProductRepositoryImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }
    @Override
    public Integer save(Product product) {
        return productMapper.save(product);
    }
}
