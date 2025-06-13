package com.app.pd.ecommerce.dao;

import com.app.pd.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItem, Long> {
}
