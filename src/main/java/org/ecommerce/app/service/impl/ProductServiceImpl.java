package org.ecommerce.app.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.ecommerce.app.persistance.entity.DiscountEntity;
import org.ecommerce.app.persistance.entity.ProductEntity;
import org.ecommerce.app.persistance.repository.ProductRepository;
import org.ecommerce.app.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author Kemal Acar
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> getProductsById(Collection<String> productIds) {
        return productRepository.findAllById(productIds);
    }

    /**
     * TODO This method has been added for testing.
     */
    @PostConstruct
    public void initDB() {
        ProductEntity product1 = ProductEntity.builder().id("001").name("Rolex").unitPrice(BigDecimal.valueOf(100))
                .discount(DiscountEntity.builder().totalPrice(BigDecimal.valueOf(200)).quantity(3).build())
                .build();

        ProductEntity product2 = ProductEntity.builder().id("002").name("Michael Kors").unitPrice(BigDecimal.valueOf(80))
                .discount(DiscountEntity.builder().totalPrice(BigDecimal.valueOf(120)).quantity(2).build())
                .build();

        ProductEntity product3 = ProductEntity.builder().id("003").name("Swatch").unitPrice(BigDecimal.valueOf(50))
                .build();
        ProductEntity product4 = ProductEntity.builder().id("004").name("Casio").unitPrice(BigDecimal.valueOf(30))
                .build();

        productRepository.saveAll(List.of(product1, product2, product3, product4));

    }


}

