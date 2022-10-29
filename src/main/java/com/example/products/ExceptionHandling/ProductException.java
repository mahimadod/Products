package com.example.products.ExceptionHandling;

import lombok.Data;

@Data
public class ProductException extends Exception {
    public ProductException(ProductExceptionEnum str){
        super(str.toString());
    }
}