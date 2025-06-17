package com.app.pd.ecommerce.dao;

import com.app.pd.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Long> {
}
