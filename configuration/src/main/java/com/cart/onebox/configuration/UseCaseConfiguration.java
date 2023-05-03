package com.cart.onebox.configuration;


import com.cart.onebox.core.dataproviders.CartRepository;
import com.cart.onebox.core.dataproviders.ProductRepository;
import com.cart.onebox.core.service.crud.cart.CartServiceImpl;
import com.cart.onebox.core.service.crud.product.ProductServiceImpl;
import com.cart.onebox.entrypoints.converter.DTOToDomainConverter;
import com.cart.onebox.entrypoints.converter.DTOToDomainConverterImpl;
import com.cart.onebox.entrypoints.converter.DomainToDTOConverter;
import com.cart.onebox.entrypoints.converter.DomainToDTOConverterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public ProductServiceImpl productListUseCase(
            ProductRepository productRepository
    ) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public CartServiceImpl cartListUseCase(
            CartRepository cartRepository,
            ProductRepository productRepository
    ) {
        return new CartServiceImpl(cartRepository, productRepository);
    }

    @Bean
    public DomainToDTOConverter domainToDTOConverter() {
        return new DomainToDTOConverterImpl();
    }

    @Bean
    public DTOToDomainConverter dtoToDomainConverter() {
        return new DTOToDomainConverterImpl();
    }
}
