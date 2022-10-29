package com.example.products.Service;

import com.example.products.Entity.Product;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
public interface HazelCastCacheService {
    Product getProductFromHazelCastCache(Integer id) throws MalformedURLException, URISyntaxException;

    Boolean putProductToHazelCastCache(Product product) throws MalformedURLException, URISyntaxException;
}
