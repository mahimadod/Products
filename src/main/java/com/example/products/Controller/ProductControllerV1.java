package com.example.products.Controller;

import com.example.products.Dtos.ProductDto;
import com.example.products.Entity.Product;
import com.example.products.ExceptionHandling.ProductException;
import com.example.products.Service.ProductService;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("/")
public class ProductControllerV1 {

    @Autowired
    ProductService productService;

    @PostMapping(value = "createproduct", produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
    }

    @PatchMapping("updateproduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("deleteproduct/{ids}")
    public ResponseEntity<Boolean> deleteProduct(@PathParam("ids") Integer ids) throws ProductException, MalformedURLException, URISyntaxException {
        return new ResponseEntity<>(productService.deleteProduct(ids), HttpStatus.OK);
    }

    @GetMapping("getall")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<Product> b=productService.getAll();
        List<ProductDto> c=b.stream().map(i->ProductDto.builder().id(i.getId()).name(i.getName()).build()).collect(Collectors.toList());
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) throws ProductException, MalformedURLException, URISyntaxException {
        Product c= productService.findProductById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
