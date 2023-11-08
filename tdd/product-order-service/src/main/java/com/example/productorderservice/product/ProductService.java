package com.example.productorderservice.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(final ProductAddRequest request) {
        final Product product = new Product(
                request.productName(),
                request.productPrice(),
                request.discountPolicy()
        );

        productPort.save(product);
    }

    public GetProductResponse findProduct(final Long productId) {
        Product product = productPort.findProductById(productId);
        return new GetProductResponse(product.getId(), product.getProductName(), product.getProductPrice(),
                product.getDiscountPolicy().name());
    }
}
