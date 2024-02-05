package org.ecommerce.app.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Kemal Acar
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDto {

    @NotEmpty
    private List<String> productIdList;
}

