package org.ecommerce.app.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Kemal Acar
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    private BigDecimal totalPrice;

}



