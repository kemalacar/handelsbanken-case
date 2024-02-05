package org.ecommerce.app.service;

import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;

/**
 * @author Kemal Acar
 */
public interface CheckoutService {
    CheckoutResponseDto calculatePrice(CheckoutRequestDto requestDto);
}
