package org.ecommerce.app.unit;

import jakarta.inject.Inject;
import org.ecommerce.app.service.CheckoutService;
import org.ecommerce.app.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BeforeAllTest {

    @Inject
    protected ProductService productService;

    @Inject
    protected CheckoutService checkoutService;

    @BeforeAll
    public void setUp() {

    }


    @AfterAll
    public void cleanUp() {
    }

}

