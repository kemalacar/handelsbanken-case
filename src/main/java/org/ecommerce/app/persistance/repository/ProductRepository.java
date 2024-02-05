package org.ecommerce.app.persistance.repository;

import org.ecommerce.app.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
