package com.cart.onebox.configuration;

import com.cart.onebox.dataproviders.repository.impl.CartRepositoryImpl;
import com.cart.onebox.dataproviders.repository.impl.ProductRepositoryImpl;
import com.cart.onebox.dataproviders.repository.mappers.CartMapper;
import com.cart.onebox.dataproviders.repository.mappers.ProductMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfiguration {

    public static final String H2_SESSION_FACTORY = "H2sessionFactory";

    @Bean(name = H2_SESSION_FACTORY)
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().addMapper(ProductMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(CartMapper.class);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "productMapper")
    public MapperFactoryBean<ProductMapper> productMapperMapperFactoryBean(@Qualifier(H2_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        MapperFactoryBean<ProductMapper> factoryBean = new MapperFactoryBean<>(ProductMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean(name = "cartMapper")
    public MapperFactoryBean<CartMapper> cartMapperMapperFactoryBean(@Qualifier(H2_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        MapperFactoryBean<CartMapper> factoryBean = new MapperFactoryBean<>(CartMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean(name = "productRepository")
    public ProductRepositoryImpl productRepository(ProductMapper productMapper) {
        return new ProductRepositoryImpl(productMapper);
    }

    @Bean(name = "cartRepository")
    public CartRepositoryImpl cartRepository(CartMapper cartMapper) {
        return new CartRepositoryImpl(cartMapper);
    }
}
