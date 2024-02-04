package org.ecommerce.app.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Kemal Acar
 */
@Data
public class CheckoutRequestDto {
    private List<String> productIdList;
}

