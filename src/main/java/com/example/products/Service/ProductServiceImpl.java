package com.example.products.Service;

import com.example.products.Dtos.ProductDto;
import com.example.products.Entity.Product;
import com.example.products.ExceptionHandling.ProductException;
import com.example.products.ExceptionHandling.ProductExceptionEnum;
import com.example.products.Repository.ProductRepository;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepositoryService;
    //OrikaMapper
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    MapperFacade mapper=mapperFactory.getMapperFacade();

    Logger logger = LoggerFactory.getLogger(Service.class);

    @Autowired
    HazelCastCacheService hazelCastCacheService;
    @Override
    public ProductDto createProduct(ProductDto productDto){
        Product productToCreate=mapper.map(productDto,Product.class);
        Product createdProduct=productRepositoryService.save(productToCreate);
        return mapper.map(createdProduct,ProductDto.class);
    }

    @Cacheable(value = "productCache1",key="#root.methodName")
    @Override
    public List<Product> getAll() {
        return productRepositoryService.findAll();
    }

    @Override
    public ProductDto updateProduct(ProductDto productToUpdate) throws ProductException, MalformedURLException, URISyntaxException {
        Product updatedProduct=new Product();
        Product existingProduct = findProductById(productToUpdate.getId());
        updatedProduct=productRepositoryService.save(mapper.map(productToUpdate,Product.class));
        return mapper.map(updatedProduct,ProductDto.class);
    }

    //@Cacheable(value = "productCache",key="#id")
    @Override
    public Product findProductById(Integer id) throws ProductException, MalformedURLException, URISyntaxException {
        Product existingProductInCache=hazelCastCacheService.getProductFromHazelCastCache(id);
        if(Objects.nonNull(existingProductInCache)){
            return existingProductInCache;
        }else {
            Optional<Product> existingProduct = productRepositoryService.findById(id);
            if (existingProduct.isPresent()) {
                logger.info("person found");
                hazelCastCacheService.putProductToHazelCastCache(existingProduct.get());
                return existingProduct.get();
            } else {
                logger.info("person not found");
                throw new ProductException(ProductExceptionEnum.PRODUCT_DOES_NOT_EXIST);
            }
        }
    }

    public Boolean deleteProduct(Integer id) throws ProductException, MalformedURLException, URISyntaxException {
        Product existingProduct=findProductById(id);
        productRepositoryService.delete(existingProduct);
        return true;
    }
}
