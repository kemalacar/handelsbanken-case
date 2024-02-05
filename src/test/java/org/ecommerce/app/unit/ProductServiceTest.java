package org.ecommerce.app.unit;

import org.ecommerce.app.persistance.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceTest extends BeforeAllTest {

    @Test
    void test_get_products_exist() {
        List<String> productIds = List.of("001", "002", "004", "003");
        List<ProductEntity> productsById = productService.getProductsById(productIds);

        Assertions.assertNotNull(productsById);
        Assertions.assertEquals(productsById.size(), productIds.size());

    }

    @Test
    void test_get_products_not_exist() {
        List<String> productIds = List.of("009");
        List<ProductEntity> productsById = productService.getProductsById(productIds);

        Assertions.assertNotNull(productsById);
        Assertions.assertEquals(productsById.size(), 0);

    }


}
