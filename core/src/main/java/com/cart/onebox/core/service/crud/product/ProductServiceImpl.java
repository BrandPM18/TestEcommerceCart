package com.cart.onebox.core.service.crud.product;

import com.cart.onebox.core.StatusCode;
import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.product.param.ProductCreateParam;
import com.cart.onebox.core.service.crud.product.param.ProductDetailParam;
import com.cart.onebox.core.service.crud.product.result.ProductCreateResult;
import com.cart.onebox.core.service.crud.product.result.ProductListResult;
import com.cart.onebox.core.service.crud.product.result.ProductDetailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductListResult getList() {
        ProductListResult result = new ProductListResult();
        try {
            List<Product> productList = productRepository.findAll();
            result.setProducts(productList);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ProductListResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }

        return result;
    }

    @Override
    public ProductDetailResult getProductDetail(ProductDetailParam param) {
        ProductDetailResult result = new ProductDetailResult();
        try {
            Product product = productRepository.findById(param.getId());
            result.setProduct(product);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ProductDetailResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }
        return result;
    }

    @Override
    public ProductCreateResult createProduct(ProductCreateParam param) {
        ProductCreateResult result = new ProductCreateResult();
        try {
            Integer id = productRepository.save(param.getProduct());
            result.setId(id);
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ProductCreateResult.builder()
                    .statusCode(StatusCode.ERROR)
                    .build();
        }
        return result;
    }
}
