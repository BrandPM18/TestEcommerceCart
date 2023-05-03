package com.cart.onebox.entrypoints.model.response;

import com.cart.onebox.entrypoints.model.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListRS {
    private List<ProductDTO> products;
}
