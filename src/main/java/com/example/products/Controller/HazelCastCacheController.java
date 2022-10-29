package com.example.products.Controller;

import com.example.products.Entity.Product;
import com.example.products.Service.HazelCastCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController("/hazelcastcache")
public class HazelCastCacheController {

    @Autowired
    HazelCastCacheService hazelCastCacheService;

    @GetMapping("getbyidincache/{id}")
    public ResponseEntity<Product> getProductFromHazelCastCache(@PathVariable("id") int id) throws MalformedURLException, URISyntaxException {
        return new ResponseEntity<Product>(hazelCastCacheService.getProductFromHazelCastCache(id), HttpStatus.OK);
    }

    @PostMapping("putbyproduct")
    public ResponseEntity<String> putProductFromHazelCastCache(@RequestBody Product product) throws MalformedURLException, URISyntaxException {
        hazelCastCacheService.putProductToHazelCastCache(product);
        return new ResponseEntity<>("request Successful", HttpStatus.OK);
    }
}
