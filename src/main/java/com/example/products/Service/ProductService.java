package com.example.products.Service;

import com.example.products.Dtos.ProductDto;
import com.example.products.Entity.Product;
import com.example.products.ExceptionHandling.ProductException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<Product> getAll();

    ProductDto updateProduct(ProductDto product)throws Exception;

    Boolean deleteProduct(Integer id) throws ProductException, MalformedURLException, URISyntaxException;
    Product findProductById(Integer id) throws ProductException, MalformedURLException, URISyntaxException;
}
