package com.example.products.Service;

import com.example.products.Entity.Product;
import com.example.products.cache.HazelCastCacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class HazelCastCacheServiceImpl implements HazelCastCacheService{
    @Autowired
    HazelCastCacheConfiguration hazelCastCacheConfiguration;
    @Override
    public Product getProductFromHazelCastCache(Integer id) throws MalformedURLException, URISyntaxException {
        Map<Integer, Product> map=hazelCastCacheConfiguration.getHazelCastCacheProductCache(hazelCastCacheConfiguration.getHazelCastCacheInstance(hazelCastCacheConfiguration.getHazelCastConfig()));
        return map.get(id);
    }

    @Override
    public Boolean putProductToHazelCastCache(Product product) throws MalformedURLException, URISyntaxException {
        Map<Integer, Product> map=hazelCastCacheConfiguration.getHazelCastCacheProductCache(hazelCastCacheConfiguration.getHazelCastCacheInstance(hazelCastCacheConfiguration.getHazelCastConfig()));
        map.put(product.getId(),product);
        return true;
    }



}
