package com.markmburu.productservice.service;

import com.markmburu.productservice.dto.ProductRequest;
import com.markmburu.productservice.dto.ProductResponse;
import com.markmburu.productservice.model.Product;
import com.markmburu.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    //constructor dependency injection
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        //creating an instance of the product
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        // save to db
        productRepository.save(product);

        log.info("product {} has been saved",product.getId());


    }

 public List<ProductResponse>getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
 }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

