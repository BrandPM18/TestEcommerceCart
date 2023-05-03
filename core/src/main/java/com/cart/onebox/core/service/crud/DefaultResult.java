package com.cart.onebox.core.service.crud;

import com.cart.onebox.core.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResult {
    private StatusCode statusCode;
    private String message;
}
