package com.cart.onebox.entrypoints;

import com.cart.onebox.core.StatusCode;
import com.cart.onebox.core.service.crud.product.param.ProductDetailParam;
import com.cart.onebox.core.service.crud.product.result.ProductCreateResult;
import com.cart.onebox.core.service.crud.product.result.ProductListResult;
import com.cart.onebox.core.service.crud.product.ProductServiceImpl;
import com.cart.onebox.core.service.crud.product.result.ProductDetailResult;
import com.cart.onebox.entrypoints.model.response.ProductCreateRS;
import com.cart.onebox.entrypoints.model.ProductDTO;
import com.cart.onebox.entrypoints.model.response.ProductListRS;
import com.cart.onebox.entrypoints.model.request.ProductRQ;
import com.cart.onebox.entrypoints.converter.DTOToDomainConverter;
import com.cart.onebox.entrypoints.converter.DomainToDTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Validated
public class ProductController {
    private final ProductServiceImpl productListUseCase;
    private DTOToDomainConverter convertToDomain;
    private DomainToDTOConverter convertToDTO;

    @GetMapping("")
    @Operation(summary = "List products", description = "List products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<?> findAll() {

        ProductListResult result = productListUseCase.getList();

        ResponseEntity responseEntity;
        if (result.getStatusCode().equals(StatusCode.SUCCESS)) {
            ProductListRS response = convertToDTO.convert(result);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product information", description = "View specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = ProductDTO.class)
            ))
    })
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Integer id) {

        ProductDetailParam param = ProductDetailParam.builder()
                .id(id)
                .build();

        ProductDetailResult result = productListUseCase.getProductDetail(param);

        ResponseEntity responseEntity;
        if (result.getStatusCode().equals(StatusCode.SUCCESS)) {
            ProductDTO response = convertToDTO.convert(result);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @Operation(summary = "Create new product", description = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductRQ productRQ) {

        ProductCreateResult result = productListUseCase.createProduct(convertToDomain.convert(productRQ));

        ResponseEntity responseEntity;
        if (result.getStatusCode().equals(StatusCode.SUCCESS)) {
            ProductCreateRS response = convertToDTO.convert(result);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
