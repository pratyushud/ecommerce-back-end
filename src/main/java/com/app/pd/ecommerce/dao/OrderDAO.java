package com.app.pd.ecommerce.dao;

import com.app.pd.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Long> {
}
