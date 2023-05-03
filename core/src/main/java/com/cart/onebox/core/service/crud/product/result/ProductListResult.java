package com.cart.onebox.core.service.crud.product.result;

import com.cart.onebox.core.domain.Product;
import com.cart.onebox.core.service.crud.DefaultResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResult extends DefaultResult {
    private List<Product> products;

}
