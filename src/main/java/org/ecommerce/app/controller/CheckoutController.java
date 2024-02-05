package org.ecommerce.app.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;
import org.ecommerce.app.service.CheckoutService;
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

    private final CheckoutService checkoutService;

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody @Valid CheckoutRequestDto checkoutRequestDto) {

        return ResponseEntity.ok(checkoutService.calculatePrice(checkoutRequestDto));
    }

}

