package org.ecommerce.app.service;

import org.ecommerce.app.persistance.entity.ProductEntity;

import java.util.Collection;
import java.util.List;

/**
 *  Product operations are implemented in this class.
 * @author Kemal Acar
 */
public interface ProductService {

    /**
     * @param productIds as {@link Collection}
     * @return  {@link ProductEntity} list of given ids
     */
    List<ProductEntity> getProductsById(Collection<String> productIds);
}
