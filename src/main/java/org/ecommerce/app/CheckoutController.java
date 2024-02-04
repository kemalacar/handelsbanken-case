package org.ecommerce.app;

import lombok.AllArgsConstructor;
import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;
import org.ecommerce.app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kemal Acar
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "checkout")
public class CheckoutController {

    private final ProductService productService;

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody CheckoutRequestDto checkoutRequestDto) {
        return ResponseEntity.ok(productService.calculateCheckoutPrice(checkoutRequestDto));
    }

}

