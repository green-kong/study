package com.example.productorderservice.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final ProductAddRequest request) {
        final Product product = new Product(
                request.productName(),
                request.productPrice(),
                request.discountPolicy()
        );

        productPort.save(product);
    }
}
