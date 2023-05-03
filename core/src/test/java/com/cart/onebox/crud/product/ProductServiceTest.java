package com.cart.onebox.crud.product;

import com.cart.onebox.core.StatusCode;
import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.product.ProductServiceImpl;
import com.cart.onebox.core.service.crud.product.param.ProductCreateParam;
import com.cart.onebox.core.service.crud.product.param.ProductDetailParam;
import com.cart.onebox.core.service.crud.product.result.ProductCreateResult;
import com.cart.onebox.core.service.crud.product.result.ProductDetailResult;
import com.cart.onebox.core.service.crud.product.result.ProductListResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ProductServiceTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
    }

    @Test
    public void testGetAllOk() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(mockProduct()));
        ProductServiceImpl productService = new ProductServiceImpl(productRepository);

        ProductListResult result = productService.getList();
        Assertions.assertEquals(StatusCode.SUCCESS, result.getStatusCode());
    }

    @Test
    public void testGetDetailOk() {
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(mockProduct());
        ProductServiceImpl productService = new ProductServiceImpl(productRepository);

        ProductDetailParam param = ProductDetailParam.builder()
                .id(1)
                .build();

        ProductDetailResult result = productService.getProductDetail(param);
        Assertions.assertEquals(StatusCode.SUCCESS, result.getStatusCode());
    }

    @Test
    public void testCreateProductOk() {
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(1);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository);

        ProductCreateParam param = ProductCreateParam.builder()
                .product(Product.builder()
                        .description("Product 1")
                        .amount(10.0F)
                        .build())
                .build();

        ProductCreateResult result = productService.createProduct(param);
        Assertions.assertEquals(StatusCode.SUCCESS, result.getStatusCode());
    }



    private Product mockProduct() {
        return Product.builder()
                .id(1)
                .description("Product 1")
                .amount(10.0F)
                .build();
    }
}
