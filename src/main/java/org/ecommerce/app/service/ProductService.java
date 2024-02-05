package org.ecommerce.app.service;

import org.ecommerce.app.persistance.entity.ProductEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author Kemal Acar
 */
public interface ProductService {
    List<ProductEntity> getProductsById(Collection<String> productIds);
}
