package com.cart.onebox.core.service.crud.cart.result;

import com.cart.onebox.core.service.crud.DefaultResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateResult extends DefaultResult {
    private UUID id;
}
