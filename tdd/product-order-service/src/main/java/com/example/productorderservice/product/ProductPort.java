package com.example.productorderservice.product;

interface ProductPort {
    void save(final Product product);

    Product findProductById(Long productId);
}
