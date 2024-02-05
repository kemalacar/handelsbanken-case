package org.ecommerce.app.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author Kemal Acar
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private BigDecimal unitPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private DiscountEntity discount;

    public boolean hasDiscount() {
        return discount != null ;
    }

}



