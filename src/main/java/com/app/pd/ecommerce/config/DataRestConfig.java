package com.app.pd.ecommerce.config;

import com.app.pd.ecommerce.entity.Country;
import com.app.pd.ecommerce.entity.Product;
import com.app.pd.ecommerce.entity.ProductCategory;
import com.app.pd.ecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unSupportedMethods = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE };
        config.getExposureConfiguration().forDomainType(Product.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable((unSupportedMethods)));

        config.getExposureConfiguration().forDomainType(ProductCategory.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable((unSupportedMethods)));

        config.getExposureConfiguration().forDomainType(Country.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable((unSupportedMethods)));

        config.getExposureConfiguration().forDomainType(State.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable((unSupportedMethods)));

        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = this.entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<Class>();
        for(EntityType entity : entities) {
            entityClasses.add(entity.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
