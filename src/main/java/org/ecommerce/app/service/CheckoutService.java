package org.ecommerce.app.service;

import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;

/**
 * This interface contains one method and contains methods are related  checkout steps.
 * @author Kemal Acar
 */
public interface CheckoutService {
    /**
     * @param requestDto  {@link CheckoutRequestDto}
     * @return {@link CheckoutResponseDto} includes calculated checkout price
     */
    CheckoutResponseDto calculatePrice(CheckoutRequestDto requestDto);
}
