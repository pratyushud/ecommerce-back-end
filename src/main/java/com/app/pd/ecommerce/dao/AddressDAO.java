package com.app.pd.ecommerce.dao;

import com.app.pd.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDAO extends JpaRepository<Address, Long> {
}
