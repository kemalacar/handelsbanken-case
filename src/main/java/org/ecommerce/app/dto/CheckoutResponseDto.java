package org.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Kemal Acar
 */
@Data
@Builder
public class CheckoutResponseDto {
    private BigDecimal price;
}

