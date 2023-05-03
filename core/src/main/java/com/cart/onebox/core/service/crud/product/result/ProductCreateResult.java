package com.cart.onebox.core.service.crud.product.result;

import com.cart.onebox.core.service.crud.DefaultResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResult extends DefaultResult {
    private Integer id;
}
